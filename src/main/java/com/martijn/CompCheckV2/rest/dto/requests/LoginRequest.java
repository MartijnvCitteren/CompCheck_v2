package com.martijn.CompCheckV2.rest.dto.requests;

import jakarta.validation.constraints.Email;

public record LoginRequest(
        @Email
        String email,
        String password
) {
}
