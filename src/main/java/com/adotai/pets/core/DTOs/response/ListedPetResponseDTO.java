package com.adotai.pets.core.DTOs.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ListedPetResponseDTO(
        String id,
        String name,
        String type,
        String breed,
        int age,
        String observations,
        String status,
        Instant createdAt
) { }
