package com.adotai.pets.core.gateways;

import com.adotai.pets.core.domain.Organization;
import com.adotai.pets.core.exceptions.EmailOrDocumentAlreadyRegistered;

import java.util.Optional;

public interface OrganizationGateway {
    Organization saveOrganization(Organization organization) throws EmailOrDocumentAlreadyRegistered;
    Optional<Organization> findByDocument(String document);
}
