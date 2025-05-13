package com.adotai.pets.core.gateways;

import com.adotai.pets.core.domain.User;
import com.adotai.pets.core.exceptions.EmailAlreadyRegisteredException;

import java.util.Optional;

public interface UserGateway {
    Optional<User> findUserByEmail(String email);
    User saveUser(User user) throws EmailAlreadyRegisteredException;
}
