package com.adotai.pets.data.mapper;

import com.adotai.pets.core.domain.User;
import com.adotai.pets.data.entities.UserEntity;

public class UserMapper {
    public static UserMapper INSTANCE = new UserMapper();

    public UserEntity toEntity(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .phone(domain.getPhone())
                .emailVerified(domain.isEmailVerified())
                .password(domain.getPassword())
                .createdAt(domain.getCreatedAt())
                .birthdate(domain.getBirthdate())
                .build();
    }

    public User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .emailVerified(entity.isEmailVerified())
                .password(entity.getPassword())
                .createdAt(entity.getCreatedAt())
                .birthdate(entity.getBirthdate())
                .build();
    }
}
