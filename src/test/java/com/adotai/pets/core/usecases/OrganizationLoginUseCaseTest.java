package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.OrganizationLoginRequestDTO;
import com.adotai.pets.core.DTOs.request.UserLoginRequestDTO;
import com.adotai.pets.core.DTOs.response.OrganizationLoginResponseDTO;
import com.adotai.pets.core.DTOs.response.UserLoginResponseDTO;
import com.adotai.pets.core.exceptions.InvalidEmailOrPasswordException;
import com.adotai.pets.core.services.AuthenticationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganizationLoginUseCaseTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private OrganizationLoginUseCase organizationLoginUseCase;

    @Test
    @DisplayName("Should login organization successfully when document and password are correct")
    void testShouldLoginUserSuccessfullyWhenEmailAndPasswordAreCorrect() {
        var request = new OrganizationLoginRequestDTO("123123123", "pass123");
        var expectedResponse = OrganizationLoginResponseDTO.builder()
                .token("authToken")
                .build();
        when(authenticationService.authenticate("123123123", "pass123"))
                .thenReturn("authToken");

        var response = organizationLoginUseCase.execute(request);
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Should throw when organization login fails with wrong password")
    void testShouldThrowWhenUserLoginFailsWithWrongPassword() {
        var request = new OrganizationLoginRequestDTO("123123123", "pass123");

        when(authenticationService.authenticate("123123123", "pass123"))
                .thenThrow(new InvalidEmailOrPasswordException());

        assertThrows(
                InvalidEmailOrPasswordException.class,
                () -> organizationLoginUseCase.execute(request)
        );
    }
}