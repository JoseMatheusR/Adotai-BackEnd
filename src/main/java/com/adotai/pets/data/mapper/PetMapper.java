package com.adotai.pets.data.mapper;

import com.adotai.pets.core.domain.Pet;
import com.adotai.pets.data.entities.PetEntity;

import java.util.Objects;

public class PetMapper {

    public static final PetMapper INSTANCE = new PetMapper();

    public PetEntity toEntity(Pet domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        return PetEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .type(domain.getType())
                .breed(domain.getBreed())
                .age(domain.getAge())
                .observations(domain.getObservations())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .organization(OrganizationMapper.INSTANCE.toEntity(domain.getOrganization()))
                .build();
    }

    public Pet toDomain(PetEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Pet.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .breed(entity.getBreed())
                .age(entity.getAge())
                .observations(entity.getObservations())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .organization(OrganizationMapper.INSTANCE.toDomain(entity.getOrganization()))
                .build();
    }
}
