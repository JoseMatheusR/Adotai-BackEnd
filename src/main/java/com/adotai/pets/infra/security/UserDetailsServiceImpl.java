package com.adotai.pets.infra.security;

import com.adotai.pets.core.domain.User;
import com.adotai.pets.core.gateways.OrganizationGateway;
import com.adotai.pets.core.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserGateway userGateway;
    private final OrganizationGateway organizationGateway;

    @Override
    public UserDetails loadUserByUsername(String usernameOrDocument) throws UsernameNotFoundException {
        if(usernameOrDocument.contains("@")) {
            var user = userGateway.findUserByEmail(usernameOrDocument);
            return new UserDetailsImpl(user.orElseThrow());
        } else {
            var organization = organizationGateway.findByDocument(usernameOrDocument);
            return new UserDetailsImpl(organization.orElseThrow());
        }
    }
}
