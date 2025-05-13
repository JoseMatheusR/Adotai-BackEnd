package com.adotai.pets.core.services;

import com.adotai.pets.core.exceptions.InvalidEmailOrPasswordException;

public interface AuthenticationService {
    String authenticate(String emailOrDocument, String password) throws InvalidEmailOrPasswordException;
}
