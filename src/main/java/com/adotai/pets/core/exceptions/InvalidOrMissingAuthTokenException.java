package com.adotai.pets.core.exceptions;

import com.adotai.pets.core.exceptions.base.BusinessException;

public class InvalidOrMissingAuthTokenException extends BusinessException {
    public InvalidOrMissingAuthTokenException(String message) {
        super(message);
    }
}
