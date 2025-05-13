package com.adotai.pets.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "Organization")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String document;
    private String name;
    private String email;
    private String password;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
