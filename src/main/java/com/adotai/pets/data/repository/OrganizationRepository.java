package com.adotai.pets.data.repository;

import com.adotai.pets.data.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, String> {
    Optional<OrganizationEntity> findByDocument(String document);
    OrganizationEntity getReferenceByDocument(String document);
}
