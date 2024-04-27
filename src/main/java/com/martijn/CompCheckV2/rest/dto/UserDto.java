package com.martijn.CompCheckV2.rest.dto;

import com.martijn.CompCheckV2.presistence.entity.UserRoles;
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
        String password,
        UserRoles role
) {
}
