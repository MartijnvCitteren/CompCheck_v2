package com.martijn.CompCheckV2.rest.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;

@Builder
public record UserDto(
        @Nullable
        Long id,
        String firstName,
        String lastName,
        String city,
        String country,
        String email,
        String password
) {
}
