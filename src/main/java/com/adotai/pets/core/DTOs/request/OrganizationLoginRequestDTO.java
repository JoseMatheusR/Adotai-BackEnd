package com.adotai.pets.core.DTOs.request;

import jakarta.validation.constraints.NotBlank;

public record OrganizationLoginRequestDTO(
        @NotBlank String document,
        @NotBlank String password
) {
}
