package com.adotai.pets.core.DTOs.request;

import jakarta.validation.constraints.NotBlank;

public record OrganizationRegisterRequestDTO (
        @NotBlank String document,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String contactPhone
) {
}
