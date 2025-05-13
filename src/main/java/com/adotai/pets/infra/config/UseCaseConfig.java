package com.adotai.pets.infra.config;

import com.adotai.pets.core.gateways.OrganizationGateway;
import com.adotai.pets.core.gateways.PetGateway;
import com.adotai.pets.core.gateways.UserGateway;
import com.adotai.pets.core.services.AuthenticationService;
import com.adotai.pets.core.services.CryptographyService;
import com.adotai.pets.core.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    UserRegisterUseCase userRegisterUseCase(
            UserGateway userGateway,
            AuthenticationService authService,
            CryptographyService cryptographyService
    ) {
        return new UserRegisterUseCase(userGateway, authService, cryptographyService);
    }

    @Bean
    UserLoginUseCase userLoginUseCase(
            AuthenticationService authService
    ) {
        return new UserLoginUseCase(authService);
    }

    @Bean
    OrganizationRegisterUseCase organizationRegisterUseCase(
            OrganizationGateway organizationGateway,
            AuthenticationService authService,
            CryptographyService cryptographyService
    ) {
        return new OrganizationRegisterUseCase(organizationGateway, authService, cryptographyService);
    }

    @Bean
    OrganizationLoginUseCase organizationLoginUseCase(
            AuthenticationService authService
    ) {
        return new OrganizationLoginUseCase(authService);
    }

    @Bean
    RegisterPetUseCase registerPetUseCase(PetGateway petGateway) {
        return new RegisterPetUseCase(petGateway);
    }

    @Bean
    ListPetsUseCase listPetsUseCase(PetGateway petGateway) {
        return new ListPetsUseCase(petGateway);
    }
}
