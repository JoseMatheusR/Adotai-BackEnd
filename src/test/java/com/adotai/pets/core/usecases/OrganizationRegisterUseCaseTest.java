package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.OrganizationRegisterRequestDTO;
import com.adotai.pets.core.DTOs.response.OrganizationRegisterResponseDTO;
import com.adotai.pets.core.domain.Organization;
import com.adotai.pets.core.exceptions.EmailOrDocumentAlreadyRegistered;
import com.adotai.pets.core.gateways.OrganizationGateway;
import com.adotai.pets.core.services.AuthenticationService;
import com.adotai.pets.core.services.CryptographyService;
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
class OrganizationRegisterUseCaseTest {
    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private CryptographyService cryptographyService;

    @Mock
    private OrganizationGateway organizationGateway;

    @InjectMocks
    private OrganizationRegisterUseCase organizationRegisterUseCase;

    @Test
    @DisplayName("Should register organization sucessfully")
    void testShouldRegisterOrganizationSucessfully() {
        var requestDTO = new OrganizationRegisterRequestDTO(
                "11122233344",
                "ong",
                "doe@test.com",
                "1234AbCd",
                "83998871233"
        );

        var orgToSave = Organization.builder()
                .name(requestDTO.name())
                .email(requestDTO.email())
                .password("encryptedPassword")
                .contactPhone(requestDTO.contactPhone())
                .document(requestDTO.document())
                .build();

        when(cryptographyService.encryptPassword(requestDTO.password()))
                .thenReturn("encryptedPassword");
        when(organizationGateway.saveOrganization(orgToSave))
                .thenReturn(orgToSave);
        when(authenticationService.authenticate(orgToSave.getDocument(), requestDTO.password()))
                .thenReturn("jwtToken");

        OrganizationRegisterResponseDTO responseDTO =  organizationRegisterUseCase.execute(requestDTO);
        var expectedResponseDTO = new OrganizationRegisterResponseDTO("jwtToken");
        assertEquals(expectedResponseDTO, responseDTO);
    }

    @Test
    @DisplayName("Should throw exception when email already registerd")
    void testShouldThrowExceptionWhenEmailAlreadyRegistered() {
        var requestDTO = new OrganizationRegisterRequestDTO(
                "11122233344",
                "ong",
                "doe@test.com",
                "1234AbCd",
                "83998871233"
        );

        var orgToSave = Organization.builder()
                .name(requestDTO.name())
                .email(requestDTO.email())
                .password("encryptedPassword")
                .contactPhone(requestDTO.contactPhone())
                .document(requestDTO.document())
                .build();

        when(cryptographyService.encryptPassword(requestDTO.password()))
                .thenReturn("encryptedPassword");
        when(organizationGateway.saveOrganization(orgToSave))
                .thenThrow(EmailOrDocumentAlreadyRegistered.class);


        assertThrows(EmailOrDocumentAlreadyRegistered.class, () -> organizationRegisterUseCase.execute(requestDTO));
    }
}