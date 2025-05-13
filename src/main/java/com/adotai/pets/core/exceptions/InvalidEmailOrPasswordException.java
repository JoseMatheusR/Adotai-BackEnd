package com.adotai.pets.core.exceptions;

import com.adotai.pets.core.exceptions.base.BusinessException;

public class InvalidEmailOrPasswordException extends BusinessException {
    public InvalidEmailOrPasswordException() {
        super("Invalid email or password");
    }
}
