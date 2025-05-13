package com.adotai.pets.core.DTOs.queryParams;

import lombok.Builder;

@Builder
public record ListPetsQueryParams(
        String name,
        String age,
        String type,
        String breed,
        String status
) {
}
