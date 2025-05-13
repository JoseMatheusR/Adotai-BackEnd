package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.OrganizationRegisterRequestDTO;
import com.adotai.pets.core.DTOs.response.OrganizationRegisterResponseDTO;
import com.adotai.pets.core.domain.Organization;
import com.adotai.pets.core.exceptions.ErrorTryingToAuthButRegisterSucessfullyException;
import com.adotai.pets.core.gateways.OrganizationGateway;
import com.adotai.pets.core.services.AuthenticationService;
import com.adotai.pets.core.services.CryptographyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrganizationRegisterUseCase {

    private final OrganizationGateway organizationGateway;
    private final AuthenticationService authenticationService;
    private final CryptographyService cryptographyService;

    public OrganizationRegisterResponseDTO execute(OrganizationRegisterRequestDTO dto) {
        var organizationToRegister = Organization.builder()
                .email(dto.email())
                .name(dto.name())
                .document(dto.document())
                .contactPhone(dto.contactPhone())
                .password(cryptographyService.encryptPassword(dto.password()))
                .emailVerified(false)
                .build();

        var createdOrganization = organizationGateway.saveOrganization(organizationToRegister);

        try {
            String authToken = authenticationService.authenticate(
                    createdOrganization.getDocument(),
                    dto.password()
            );
            return new OrganizationRegisterResponseDTO(authToken);
        }  catch (Exception e) {
            throw new ErrorTryingToAuthButRegisterSucessfullyException("Error while trying to authenticate, but organization was registered sucessfully");
        }
    }
}
