package com.sergioricart.kafka_project.user.infrastructure.database;

import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final List<UserEntity> userEntityList = new ArrayList<>();

    private final UserEntityMapper userEntityMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);

        userEntityList.stream().forEach(entity -> {
            log.info("Saving User: {}", entity);
        });

        userEntityList.add(userEntity);

        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return userEntityList.stream().filter(
                userEntity -> userEntity.getId()
                        .equals(id))
                .findFirst()
                .map(userEntityMapper::mapToUser);
    }

    @Override
    public void deleteById(String id) {
        userEntityList.removeIf(userEntity -> userEntity.getId().equals(id));
    }

    @Override
    public boolean existByEmail(String email) {
        return userEntityList.stream()
                .anyMatch(userEntity -> userEntity.getEmail().equals(email));
    }
}
