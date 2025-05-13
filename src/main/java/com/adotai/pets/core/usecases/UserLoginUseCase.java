package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.UserLoginRequestDTO;
import com.adotai.pets.core.DTOs.response.UserLoginResponseDTO;
import com.adotai.pets.core.services.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserLoginUseCase {

    private final AuthenticationService authenticationService;

    public UserLoginResponseDTO execute(UserLoginRequestDTO dto) {
        String token = authenticationService.authenticate(dto.email(), dto.password());
        return new UserLoginResponseDTO(token);
    }
}
