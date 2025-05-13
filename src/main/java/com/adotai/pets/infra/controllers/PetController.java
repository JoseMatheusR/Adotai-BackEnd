package com.adotai.pets.infra.controllers;


import com.adotai.pets.core.DTOs.queryParams.ListPetsQueryParams;
import com.adotai.pets.core.DTOs.request.RegisterPetRequestDTO;
import com.adotai.pets.core.DTOs.response.ListedPetResponseDTO;
import com.adotai.pets.core.DTOs.response.RegisterPetResponseDTO;
import com.adotai.pets.core.usecases.ListPetsUseCase;
import com.adotai.pets.core.usecases.RegisterPetUseCase;
import com.adotai.pets.infra.security.utils.AuthUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final RegisterPetUseCase registerPetUseCase;
    private final ListPetsUseCase listPetsUseCase;

    @GetMapping
    ResponseEntity<List<ListedPetResponseDTO>> filterPets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) String status
    ) {
        String organizationDocument = AuthUtils.getSubjectFromAuth();
        var filter = ListPetsQueryParams.builder()
                .name(name)
                .age(age)
                .type(type)
                .breed(breed)
                .status(status)
                .build();

        var response = listPetsUseCase.execute(filter, organizationDocument);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    ResponseEntity<RegisterPetResponseDTO> registerPet(
            @RequestBody @Valid RegisterPetRequestDTO requestDTO
    ) {
        String organizationDocument = AuthUtils.getSubjectFromAuth();
        var response = registerPetUseCase.execute(requestDTO, organizationDocument);
        return ResponseEntity.status(201).body(response);
    }
}
