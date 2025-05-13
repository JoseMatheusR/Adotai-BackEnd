package com.adotai.pets.core.DTOs.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDTO(
        @NotBlank String email,
        @NotBlank String password
) {
}
