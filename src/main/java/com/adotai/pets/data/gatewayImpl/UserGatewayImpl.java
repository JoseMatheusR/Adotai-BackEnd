package com.adotai.pets.data.gatewayImpl;

import com.adotai.pets.core.domain.User;
import com.adotai.pets.core.exceptions.EmailAlreadyRegisteredException;
import com.adotai.pets.core.gateways.UserGateway;
import com.adotai.pets.data.mapper.UserMapper;
import com.adotai.pets.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByEmail(String email) {
        var userEntity = userRepository.findByEmail(email);
        return userEntity.map(entity -> UserMapper.INSTANCE.toDomain(entity));
    }

    @Override
    public User saveUser(User user) throws EmailAlreadyRegisteredException {
        var userEntity = UserMapper.INSTANCE.toEntity(user);
        try {
            var createdUserEntity = userRepository.save(userEntity);
            return UserMapper.INSTANCE.toDomain(createdUserEntity);

        } catch (DataIntegrityViolationException e) {

            throw new EmailAlreadyRegisteredException("Email is already registered");
        }

    }
}
