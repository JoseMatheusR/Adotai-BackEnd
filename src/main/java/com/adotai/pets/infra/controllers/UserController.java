package com.adotai.pets.infra.controllers;

import com.adotai.pets.core.DTOs.request.UserLoginRequestDTO;
import com.adotai.pets.core.DTOs.request.UserRegisterRequestDTO;
import com.adotai.pets.core.DTOs.response.UserLoginResponseDTO;
import com.adotai.pets.core.DTOs.response.UserRegisterResponseDTO;
import com.adotai.pets.core.usecases.UserLoginUseCase;
import com.adotai.pets.core.usecases.UserRegisterUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRegisterUseCase userRegisterUseCase;
    private final UserLoginUseCase userLoginUseCase;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> userLogin(
            @RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO
    ) {
        var responseDTO = userLoginUseCase.execute(userLoginRequestDTO);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> userRegister(
            @RequestBody @Valid UserRegisterRequestDTO request
    ) {
        var responseDTO = userRegisterUseCase.execute(request);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping("/testauth")
    public String testAuth() {
        return "You're authenticated!";
    }
}
