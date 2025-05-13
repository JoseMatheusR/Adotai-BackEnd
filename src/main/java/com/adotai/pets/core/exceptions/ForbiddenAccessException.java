package com.adotai.pets.core.exceptions;

import com.adotai.pets.core.exceptions.base.BusinessException;

public class ForbiddenAccessException extends BusinessException {
    public ForbiddenAccessException(String message) {
        super(message);
    }
}
