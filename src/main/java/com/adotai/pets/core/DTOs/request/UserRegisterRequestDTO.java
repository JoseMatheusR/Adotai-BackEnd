package com.adotai.pets.core.DTOs.request;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequestDTO (
    @NotBlank String name,
    @NotBlank String email,
    @NotBlank String password,
    @NotBlank String birthDate,
    @NotBlank String phone
) {
}
