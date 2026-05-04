package com.sergioricart.kafka_project.user.domain.port;

import com.sergioricart.kafka_project.user.domain.entiry.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    boolean existByEmail(String email);

}
