package com.sergioricart.kafka_project.user.infrastructure.database;

import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.domain.port.UserRepository;
import com.sergioricart.kafka_project.user.infrastructure.api.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityMapper userEntityMapper;
    private final UserRepositoryData userRepositoryData;

    // SI VIENE DESDE EL DOMAIN
    @Override
    public void save(User user) {

        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);

        userRepositoryData.save(userEntity);

    }

    // SI VIENE DESDE LA API
    @Override
    public void save(UserResponse user) {

        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);

        userRepositoryData.save(userEntity);

    }

    @Override
    public Optional<UserResponse> findById(String id) {

        return userRepositoryData.findById(id)
                .map(userEntityMapper::mapToUserResponse);

    }

    @Override
    public void deleteById(String id) {
        userRepositoryData.deleteById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepositoryData.existsByEmail(email);
    }
}
