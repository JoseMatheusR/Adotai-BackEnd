package com.adotai.pets.core.exceptions;

import com.adotai.pets.core.exceptions.base.BusinessException;

public class EmailOrDocumentAlreadyRegistered extends BusinessException {
    public EmailOrDocumentAlreadyRegistered(String message) {
        super(message);
    }
}
