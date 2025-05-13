package com.adotai.pets.infra.exceptionHandlers.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionDTO(
        String message,
        Map<String, String> errors
) {
}
