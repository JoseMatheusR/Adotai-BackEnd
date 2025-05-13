package com.adotai.pets.core.exceptions;

import com.adotai.pets.core.exceptions.base.BusinessException;

public class ErrorTryingToAuthButRegisterSucessfullyException extends BusinessException {
    public ErrorTryingToAuthButRegisterSucessfullyException(String message) {
        super(message);
    }
}
