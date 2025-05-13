package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.UserLoginRequestDTO;
import com.adotai.pets.core.DTOs.response.UserLoginResponseDTO;
import com.adotai.pets.core.exceptions.InvalidEmailOrPasswordException;
import com.adotai.pets.core.services.AuthenticationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserLoginUseCaseTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private UserLoginUseCase userLoginUseCase;

    @Test
    @DisplayName("Should login user successfully when email and password are correct")
    void testShouldLoginUserSuccessfullyWhenEmailAndPasswordAreCorrect() {
        var request = new UserLoginRequestDTO("teste@email.com", "pass123");
        var expectedResponse = new UserLoginResponseDTO("authToken");
        when(authenticationService.authenticate("teste@email.com", "pass123"))
                .thenReturn("authToken");

        var response = userLoginUseCase.execute(request);
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Should throw when user login fails with wrong password")
    void testShouldThrowWhenUserLoginFailsWithWrongPassword() {
        var request = new UserLoginRequestDTO("teste@email.com", "pass123");

        when(authenticationService.authenticate("teste@email.com", "pass123"))
                .thenThrow(new InvalidEmailOrPasswordException());

        assertThrows(
                InvalidEmailOrPasswordException.class,
                () -> userLoginUseCase.execute(request)
        );
    }
}