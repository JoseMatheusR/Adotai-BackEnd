package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.UserRegisterRequestDTO;
import com.adotai.pets.core.DTOs.response.UserRegisterResponseDTO;
import com.adotai.pets.core.domain.User;
import com.adotai.pets.core.exceptions.EmailAlreadyRegisteredException;
import com.adotai.pets.core.gateways.UserGateway;
import com.adotai.pets.core.services.AuthenticationService;
import com.adotai.pets.core.services.CryptographyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRegisterUseCaseTest {
    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private CryptographyService cryptographyService;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private UserRegisterUseCase userRegisterUseCase;

    @Test
    @DisplayName("Should register user sucessfully")
    void testShouldRegisterUserSucessfully() {
        var requestDTO = new UserRegisterRequestDTO("John Doe",
                "doe@test.com",
                "1234AbCd",
                "01-01-2003",
                "83998871233"
        );

        var userToSave = User.builder()
                .name(requestDTO.name())
                .email(requestDTO.email())
                .password("encryptedPassword")
                .phone(requestDTO.phone())
                .birthdate(requestDTO.birthDate())
                .build();

        when(cryptographyService.encryptPassword(requestDTO.password()))
                .thenReturn("encryptedPassword");
        when(userGateway.saveUser(userToSave))
                .thenReturn(userToSave);
        when(authenticationService.authenticate(userToSave.getEmail(), requestDTO.password()))
                .thenReturn("jwtToken");

        UserRegisterResponseDTO responseDTO =  userRegisterUseCase.execute(requestDTO);
        var expectedResponseDTO = new UserRegisterResponseDTO("jwtToken");
        assertEquals(expectedResponseDTO, responseDTO);
    }

    @Test
    @DisplayName("Should throw exception when email already registerd")
    void testShouldThrowExceptionWhenEmailAlreadyRegistered() {
        var requestDTO = new UserRegisterRequestDTO("John Doe",
                "doe@test.com",
                "1234AbCd",
                "01-01-2003",
                "83998871233"
        );

        var userToSave = User.builder()
                .name(requestDTO.name())
                .email(requestDTO.email())
                .password("encryptedPassword")
                .phone(requestDTO.phone())
                .birthdate(requestDTO.birthDate())
                .build();

        when(cryptographyService.encryptPassword(requestDTO.password()))
                .thenReturn("encryptedPassword");

        when(userGateway.saveUser(userToSave)).thenThrow(EmailAlreadyRegisteredException.class);

        assertThrows(EmailAlreadyRegisteredException.class, () -> userRegisterUseCase.execute(requestDTO));
    }
}