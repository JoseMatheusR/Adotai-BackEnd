package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.OrganizationLoginRequestDTO;
import com.adotai.pets.core.DTOs.response.OrganizationLoginResponseDTO;
import com.adotai.pets.core.services.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrganizationLoginUseCase {

    private final AuthenticationService authenticationService;

    public OrganizationLoginResponseDTO execute(OrganizationLoginRequestDTO dto) {
        String token = authenticationService.authenticate(dto.document(), dto.password());
        return OrganizationLoginResponseDTO.builder()
                .token(token)
                .build();
    }
}
