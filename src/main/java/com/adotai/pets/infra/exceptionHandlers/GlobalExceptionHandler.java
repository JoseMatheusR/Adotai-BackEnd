package com.adotai.pets.infra.exceptionHandlers;

import com.adotai.pets.core.exceptions.ErrorTryingToAuthButRegisterSucessfullyException;
import com.adotai.pets.core.exceptions.base.BusinessException;
import com.adotai.pets.infra.exceptionHandlers.DTOs.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDTO> generalExceptionHandler(BusinessException ex) {
        return ResponseEntity.status(400).body(
                ExceptionDTO.builder()
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage
                ));
        return ResponseEntity.badRequest().body(
                ExceptionDTO.builder()
                        .message("invalid request body received")
                        .errors(errors)
                        .build()
        );
    }

    @ExceptionHandler(ErrorTryingToAuthButRegisterSucessfullyException.class)
    public ResponseEntity<ExceptionDTO> registerSucessfullyButAuthFailed(ErrorTryingToAuthButRegisterSucessfullyException ex) {
        return ResponseEntity.status(201).body(
                ExceptionDTO.builder()
                        .message(ex.getMessage())
                        .build()
        );
    }
}
