package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.RegisterPetRequestDTO;
import com.adotai.pets.core.DTOs.response.RegisterPetResponseDTO;
import com.adotai.pets.core.domain.Pet;
import com.adotai.pets.core.gateways.PetGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterPetUseCase {

    private final PetGateway petGateway;

    public RegisterPetResponseDTO execute(RegisterPetRequestDTO dto, String orgDocument) {
        var petToRegister = Pet.builder()
            .name(dto.name())
            .age(dto.age())
            .type(dto.type())
            .breed(dto.breed())
            .status("UNAVAILABLE")
            .observations(dto.observations())
            .build();

        var registeredPet = petGateway.savePet(petToRegister, orgDocument);

        return RegisterPetResponseDTO.builder()
                .id(registeredPet.getId())
                .name(registeredPet.getName())
                .age(registeredPet.getAge())
                .type(registeredPet.getType())
                .breed(registeredPet.getBreed())
                .observations(registeredPet.getObservations())
                .build();
    }
}
