package com.adotai.pets.infra.servicesImpl;

import com.adotai.pets.core.exceptions.InvalidEmailOrPasswordException;
import com.adotai.pets.core.services.AuthenticationService;
import com.adotai.pets.infra.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityAuthenticationService implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String authenticate(String emailOrDocument, String password) throws InvalidEmailOrPasswordException {
        try {
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(emailOrDocument, password);
            Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            return jwtService.generateToken(userDetails.getUsername());
        } catch (AuthenticationException e) {
            throw new InvalidEmailOrPasswordException();
        }

    }
}
