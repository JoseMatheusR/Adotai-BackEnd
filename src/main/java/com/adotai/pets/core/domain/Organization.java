package com.adotai.pets.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    private String id;
    private String document;
    private String name;
    private String email;
    private String password;
    private String contactPhone;
    private boolean emailVerified;
    private Instant createdAt;
}
