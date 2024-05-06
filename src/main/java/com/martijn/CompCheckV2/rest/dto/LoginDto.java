package com.martijn.CompCheckV2.rest.dto;

import lombok.Builder;

@Builder
public record LoginDto(
        String email,
        String password
) {
}
