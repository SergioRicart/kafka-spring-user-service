package com.sergioricart.kafka_project.user.domain.port;

import com.sergioricart.kafka_project.user.domain.entiry.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(String id);

    void update(User user);
}
