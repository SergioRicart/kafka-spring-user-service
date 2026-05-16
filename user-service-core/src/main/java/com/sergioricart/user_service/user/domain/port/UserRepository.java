package com.sergioricart.user_service.user.domain.port;

import com.sergioricart.user_service.user.domain.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void update(User user);

    Optional<User> findById(String id);

    Page<User> findAll(int page, int size, String q);

}
