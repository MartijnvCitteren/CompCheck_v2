package com.martijn.CompCheckV2.rest.response;

import lombok.Builder;

@Builder
public record UserResponse(
        String firstName,
        String lastName,
        String city,
        String country,
        String email
) {
}
