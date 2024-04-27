package com.martijn.CompCheckV2.rest.dto.requests;

import com.martijn.CompCheckV2.presistence.entity.UserRoles;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserRegisterRequest(
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
        String password,
        @Nullable
        UserRoles role
) {
}
