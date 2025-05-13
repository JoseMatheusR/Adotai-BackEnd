package com.adotai.pets.infra.controllers;

import com.adotai.pets.core.DTOs.request.OrganizationLoginRequestDTO;
import com.adotai.pets.core.DTOs.request.OrganizationRegisterRequestDTO;
import com.adotai.pets.core.DTOs.response.OrganizationLoginResponseDTO;
import com.adotai.pets.core.DTOs.response.OrganizationRegisterResponseDTO;
import com.adotai.pets.core.usecases.OrganizationLoginUseCase;
import com.adotai.pets.core.usecases.OrganizationRegisterUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationRegisterUseCase organizationRegisterUseCase;
    private final OrganizationLoginUseCase organizationLoginUseCase;

    @PostMapping("/register")
    ResponseEntity<OrganizationRegisterResponseDTO> orgRegister(
            @RequestBody @Valid OrganizationRegisterRequestDTO requestDTO
    ) {
        var responseDTO = organizationRegisterUseCase.execute(requestDTO);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @PostMapping("/login")
    ResponseEntity<OrganizationLoginResponseDTO> orgLogin(
            @RequestBody @Valid OrganizationLoginRequestDTO requestDTO
    ) {
        var responseDTO = organizationLoginUseCase.execute(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/testauth")
    String testAuth() {
        return "yeaaaah buddy! logged in: organization profile";
    }
}
