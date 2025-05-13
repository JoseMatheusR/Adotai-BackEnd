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
public class Pet {
    private String id;
    private String name;
    private String type;
    private String breed;
    private int age;
    private String observations;
    private String status;
    private Instant createdAt;
    private Organization organization;
}
