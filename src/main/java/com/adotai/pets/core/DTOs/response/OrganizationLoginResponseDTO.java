package com.adotai.pets.core.DTOs.response;

import lombok.Builder;

@Builder
public record OrganizationLoginResponseDTO(
        String token
) {
}
