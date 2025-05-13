package com.adotai.pets.data.gatewayImpl;

import com.adotai.pets.core.domain.Organization;
import com.adotai.pets.core.exceptions.EmailOrDocumentAlreadyRegistered;
import com.adotai.pets.core.gateways.OrganizationGateway;
import com.adotai.pets.data.mapper.OrganizationMapper;
import com.adotai.pets.data.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrganizationGatewayImpl implements OrganizationGateway {

    private final OrganizationRepository organizationRepository;

    @Override
    public Organization saveOrganization(Organization organization) throws EmailOrDocumentAlreadyRegistered {
        var organizationEntity = OrganizationMapper.INSTANCE.toEntity(organization);
        try {
            var savedOrgEntity = organizationRepository.save(organizationEntity);
            return OrganizationMapper.INSTANCE.toDomain(savedOrgEntity);
        } catch (DataIntegrityViolationException e) {
            throw new EmailOrDocumentAlreadyRegistered("Email or document is already registered by other organization");
        }
    }

    @Override
    public Optional<Organization> findByDocument(String document) {
        var organizationFoundOptional = organizationRepository.findByDocument(document);
        return organizationFoundOptional.map(org -> OrganizationMapper.INSTANCE.toDomain(org));
    }
}
