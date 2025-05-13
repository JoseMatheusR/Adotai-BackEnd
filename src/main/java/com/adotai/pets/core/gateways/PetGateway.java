package com.adotai.pets.core.gateways;

import com.adotai.pets.core.DTOs.queryParams.ListPetsQueryParams;
import com.adotai.pets.core.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetGateway {
    Pet savePet(Pet pet, String organizationDocument);
    List<Pet> filterPetsByValues(ListPetsQueryParams params, String organizationDocument);
    Optional<Pet> findPetById(int id);
}
