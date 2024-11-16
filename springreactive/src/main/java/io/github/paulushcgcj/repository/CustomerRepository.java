package io.github.paulushcgcj.repository;

import io.github.paulushcgcj.entity.CustomerEntity;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<CustomerEntity, UUID> {

}
