package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.queryParams.ListPetsQueryParams;
import com.adotai.pets.core.DTOs.response.ListedPetResponseDTO;
import com.adotai.pets.core.domain.Pet;
import com.adotai.pets.core.gateways.PetGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListPetsUseCase {

    private final PetGateway petGateway;

    public List<ListedPetResponseDTO> execute(ListPetsQueryParams params, String orgDocument) {
        List<Pet> pets = petGateway.filterPetsByValues(
                params, orgDocument
        );

        return pets.stream().map(pet -> ListedPetResponseDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .breed(pet.getBreed())
                .type(pet.getType())
                .status(pet.getStatus())
                .observations(pet.getObservations())
                .createdAt(pet.getCreatedAt())
                .build()).toList();
    }
}
