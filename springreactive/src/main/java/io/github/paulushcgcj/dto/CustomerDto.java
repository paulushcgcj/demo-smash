package io.github.paulushcgcj.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record CustomerDto(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String addressLine1,
    String addressLine2,
    String city,
    String state,
    String postalCode,
    String country
) {
}
