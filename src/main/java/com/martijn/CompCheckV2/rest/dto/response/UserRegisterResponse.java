package com.martijn.CompCheckV2.rest.dto.response;

import lombok.Builder;

@Builder
public record UserRegisterResponse(
        String firstName,
        String lastName,
        String city,
        String country,
        String email
) {
}
