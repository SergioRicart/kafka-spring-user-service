package com.sergioricart.user_service.user.domain.port;

import com.sergioricart.user_service.user.domain.entiry.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void update(User user);

    Optional<User> findById(String id);


}
