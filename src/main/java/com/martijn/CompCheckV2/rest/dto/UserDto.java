package com.martijn.CompCheckV2.rest.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String firstName,
        String lastName,
        String city,
        String country,
        String email,
        String password
) {
}
