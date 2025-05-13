package com.adotai.pets.infra.security;

import com.adotai.pets.core.domain.Organization;
import com.adotai.pets.core.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Getter
public class UserDetailsImpl implements UserDetails {
    User user;
    Organization organization;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public UserDetailsImpl(Organization organization) {
        this.organization = organization;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role;
        if (Objects.nonNull(organization)) {
            role = "ROLE_ORGANIZATION";
        } else role = "ROLE_USER";

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() {
        if (Objects.nonNull(organization)) return organization.getPassword();
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        if (Objects.nonNull(organization)) return organization.getDocument();
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
