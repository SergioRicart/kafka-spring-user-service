package com.sergioricart.kafkaSpringUserService.user.domain.port;

import com.sergioricart.kafkaSpringUserService.user.domain.entiry.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void update(User user);

    Optional<User> findById(String id);


}
