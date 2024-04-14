package com.martijn.CompCheckV2.rest.dto.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Valid
public record UserRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String city,
        @NotBlank
        String country,
        @Email
        String email,
        @NotBlank
        String password
) {
}
