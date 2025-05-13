package com.adotai.pets.core.exceptions.base;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
