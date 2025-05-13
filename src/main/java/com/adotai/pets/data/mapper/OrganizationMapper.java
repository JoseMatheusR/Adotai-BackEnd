package com.adotai.pets.data.mapper;

import com.adotai.pets.core.domain.Organization;
import com.adotai.pets.data.entities.OrganizationEntity;

import java.util.Objects;

public class OrganizationMapper {
    public static OrganizationMapper INSTANCE = new OrganizationMapper();

    public OrganizationEntity toEntity(Organization domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        return OrganizationEntity.builder()
                .id(domain.getId())
                .document(domain.getDocument())
                .name(domain.getName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .contactPhone(domain.getContactPhone())
                .emailVerified(domain.isEmailVerified())
                .createdAt(domain.getCreatedAt())
                .build();
    }

    public Organization toDomain(OrganizationEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Organization.builder()
                .id(entity.getId())
                .document(entity.getDocument())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .contactPhone(entity.getContactPhone())
                .emailVerified(entity.isEmailVerified())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
