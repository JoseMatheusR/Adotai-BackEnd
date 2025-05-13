package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.UserRegisterRequestDTO;
import com.adotai.pets.core.DTOs.response.UserRegisterResponseDTO;
import com.adotai.pets.core.domain.User;
import com.adotai.pets.core.exceptions.EmailAlreadyRegisteredException;
import com.adotai.pets.core.exceptions.ErrorTryingToAuthButRegisterSucessfullyException;
import com.adotai.pets.core.gateways.UserGateway;
import com.adotai.pets.core.services.AuthenticationService;
import com.adotai.pets.core.services.CryptographyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserRegisterUseCase {
    private final UserGateway userGateway;
    private final AuthenticationService authenticationService;
    private final CryptographyService cryptographyService;

    public UserRegisterResponseDTO execute(UserRegisterRequestDTO dto) throws EmailAlreadyRegisteredException {
        var userToRegister = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(cryptographyService.encryptPassword(dto.password()))
                .phone(dto.phone())
                .birthdate(dto.birthDate())
                .build();

        var createdUser = userGateway.saveUser(userToRegister);

        try {
            String authToken = authenticationService.authenticate(createdUser.getEmail(), dto.password());
            return new UserRegisterResponseDTO(authToken);
        } catch (Exception e) {
            throw new ErrorTryingToAuthButRegisterSucessfullyException("Error while trying to authenticate, but user was registered sucessfully");
        }
    }
}
