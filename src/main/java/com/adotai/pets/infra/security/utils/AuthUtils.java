package com.adotai.pets.infra.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class AuthUtils {

    public static String getSubjectFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(auth)) return null;

        return auth.getName();
    }
}
