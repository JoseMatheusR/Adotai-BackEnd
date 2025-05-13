package com.adotai.pets.infra.controllers;

import com.adotai.pets.infra.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAuthController {
    @Autowired
    JwtService jwtService;

    //TODO: Apagar controller após os testes e após isso criar o UserController

    @GetMapping("/login")
    ResponseEntity<String> getToken() {
        return ResponseEntity.ok(jwtService.generateToken("teste@email.com"));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    ResponseEntity<String> testRoleAuth() {
        return ResponseEntity.ok("yeaah buddy");
    }
}
