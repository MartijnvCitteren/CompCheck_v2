package com.martijn.CompCheckV2.rest.dto.requests;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record LoginRequest(
        @Email
        String email,
        String password
) {
}
