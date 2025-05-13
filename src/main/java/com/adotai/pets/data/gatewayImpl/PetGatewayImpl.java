package com.adotai.pets.data.gatewayImpl;

import com.adotai.pets.core.DTOs.queryParams.ListPetsQueryParams;
import com.adotai.pets.core.domain.Pet;
import com.adotai.pets.core.gateways.PetGateway;
import com.adotai.pets.data.mapper.PetMapper;
import com.adotai.pets.data.repository.OrganizationRepository;
import com.adotai.pets.data.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PetGatewayImpl implements PetGateway {

    private final PetRepository petRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    public Pet savePet(Pet pet, String organizationDocument) {
        var organizationReference = organizationRepository.getReferenceByDocument(organizationDocument);

        var petEntity = PetMapper.INSTANCE.toEntity(pet);
        petEntity.setOrganization(organizationReference);

        var registeredPet = petRepository.save(petEntity);

        return PetMapper.INSTANCE.toDomain(registeredPet);
    }

    @Override
    public List<Pet> filterPetsByValues(ListPetsQueryParams params, String organizationDocument) {
        var organizationReference = organizationRepository.getReferenceByDocument(organizationDocument);

        var petEntities = petRepository.filterByValues(
                Objects.nonNull(params.name()) ? params.name().toLowerCase() : null,
                params.age(),
                Objects.nonNull(params.type()) ? params.type().toLowerCase() : null,
                Objects.nonNull(params.breed()) ? params.breed().toLowerCase() : null,
                Objects.nonNull(params.status()) ? params.status().toLowerCase() : null,
                organizationReference
        );

        return petEntities.stream().map(PetMapper.INSTANCE::toDomain).toList();
    }

    @Override
    public Optional<Pet> findPetById(int id) {
        return Optional.empty();
    }
}
