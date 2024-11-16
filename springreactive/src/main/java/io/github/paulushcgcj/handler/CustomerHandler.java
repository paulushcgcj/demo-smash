package io.github.paulushcgcj.handler;

import com.github.fge.jsonpatch.JsonPatch;
import io.github.paulushcgcj.dto.CustomerDto;
import io.github.paulushcgcj.service.CustomerService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerHandler {

  private final CustomerService service;

  @GetMapping
  public Flux<CustomerDto> getAllCustomers() {
    return service.getAllCustomers();
  }

  @GetMapping("/{id}")
  public Mono<CustomerDto> getCustomerById(@PathVariable UUID id) {
    return service.getCustomerById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> saveCustomer(@RequestBody CustomerDto customer) {
    return service.saveCustomer(customer);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Mono<Void> updateCustomer(@PathVariable UUID id, @RequestBody CustomerDto customer) {
    return service.updateCustomer(id, customer);
  }

  @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Mono<Void> patchCustomer(
      @PathVariable UUID id,
      @RequestBody JsonPatch customer
  ) {
    log.info("patchCustomer: id={}, customer={}", id, customer);
    return service.patchCustomer(id, customer);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteCustomer(@PathVariable UUID id) {
    return service.deleteCustomer(id);
  }


}
