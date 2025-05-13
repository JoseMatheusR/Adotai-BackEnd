package com.adotai.pets.core.DTOs.response;

import lombok.Builder;

@Builder
public record RegisterPetResponseDTO(
        String id,
        String name,
        String type,
        String breed,
        int age,
        String observations
) {
}
