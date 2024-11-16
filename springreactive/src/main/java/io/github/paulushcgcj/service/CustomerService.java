package io.github.paulushcgcj.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.fge.jsonpatch.JsonPatch;
import io.github.paulushcgcj.dto.CustomerDto;
import io.github.paulushcgcj.entity.CustomerEntity;
import io.github.paulushcgcj.exceptions.CannotApplyPatchException;
import io.github.paulushcgcj.repository.CustomerRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;
  private final ObjectMapper mapper;

  public Flux<CustomerDto> getAllCustomers() {
    return repository
        .findAll()
        .map(entity -> new CustomerDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getState(),
                entity.getPostalCode(),
                entity.getCountry()
            )
        );
  }

  public Mono<CustomerDto> getCustomerById(UUID id) {
    return repository
        .findById(id)
        .map(entity -> new CustomerDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getState(),
                entity.getPostalCode(),
                entity.getCountry()
            )
        )
        .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")));
  }

  public Mono<Void> saveCustomer(CustomerDto customer) {
    return repository
        .save(
            new CustomerEntity(
                customer.id(),
                customer.firstName(),
                customer.lastName(),
                customer.email(),
                customer.phoneNumber(),
                customer.addressLine1(),
                customer.addressLine2(),
                customer.city(),
                customer.state(),
                customer.postalCode(),
                customer.country(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "jhonny",
                "jhonny",
                1
            )
        )
        .then();
  }

  public Mono<Void> updateCustomer(UUID id, CustomerDto customer) {
    log.info("Updating customer with id: {}", id);
    return repository
        .findById(id)
        .map(oldEntity -> new CustomerEntity(
                oldEntity.getId(),
                customer.firstName(),
                customer.lastName(),
                customer.email(),
                customer.phoneNumber(),
                customer.addressLine1(),
                customer.addressLine2(),
                customer.city(),
                customer.state(),
                customer.postalCode(),
                customer.country(),
                oldEntity.getCreationDate(),
                LocalDateTime.now(),
                oldEntity.getCreatedByUser(),
                "jhonny",
                oldEntity.getRevision() + 1
            )
        )

        .flatMap(repository::save)
        .then();
  }

  public Mono<Void> deleteCustomer(UUID id) {
    return repository.deleteById(id);
  }

  public Mono<Void> patchCustomer(UUID id, JsonPatch customerPatch) {
    return repository
        .findById(id)
        .map(entity -> applyPatchToCustomer(customerPatch, entity))
        .map(entity -> entity.withRevision(entity.getRevision() + 1))
        .flatMap(repository::save)
        .then();
  }

  private CustomerEntity applyPatchToCustomer(
      JsonPatch patch,
      CustomerEntity targetCustomer
  ) {
    try {
      JsonNode node = mapper.convertValue(targetCustomer, JsonNode.class);
      JsonNode filteredPatchNode = filterPatchOperations(patch, List.of("/id"));
      JsonPatch filteredPatch = mapper.treeToValue(filteredPatchNode, JsonPatch.class);
      JsonNode patched = filteredPatch.apply(node);
      return mapper.treeToValue(patched, CustomerEntity.class);
    } catch (Exception e) {
      throw new CannotApplyPatchException("Error while applying patch to customer");
    }
  }

  private JsonNode filterPatchOperations(
      JsonPatch patch,
      List<String> restrictedPaths
  ) {

    ArrayNode filteredNode = mapper.createArrayNode();

    mapper.convertValue(patch, JsonNode.class).forEach(operation -> {
      String path = operation.get("path").asText();
      if (restrictedPaths.stream().noneMatch(path::equals)) {
        filteredNode.add(operation);
      }
    });

    return filteredNode;
  }
}
