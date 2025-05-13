package com.adotai.pets.infra.exceptionHandlers;

import com.adotai.pets.core.exceptions.ForbiddenAccessException;
import com.adotai.pets.core.exceptions.InvalidOrMissingAuthTokenException;
import com.adotai.pets.infra.exceptionHandlers.DTOs.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthControllerExceptionHandler {

    @ExceptionHandler(ForbiddenAccessException.class)
    public ResponseEntity<ExceptionDTO> forbiddenAccessExceptionHandler(ForbiddenAccessException ex) {
        return ResponseEntity.status(403).body(
                ExceptionDTO.builder()
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(InvalidOrMissingAuthTokenException.class)
    public ResponseEntity<ExceptionDTO> invalidOrMissingTokenExceptionHandler(InvalidOrMissingAuthTokenException ex) {
        return ResponseEntity.status(401).body(
                ExceptionDTO.builder()
                        .message(ex.getMessage())
                        .build()
        );
    }
}
