package com.adotai.pets.core.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterPetRequestDTO(
        @NotBlank String name,
        @NotBlank String type,
        String breed,
        int age,
        String observations
) {
}
