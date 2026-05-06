package com.sergioricart.kafka_project.user.domain.port;

import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.infrastructure.api.dto.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {

    void save(User user);
    void save(UserResponse user);

    Optional<UserResponse> findById(String id);

    void deleteById(String id);

    boolean existByEmail(String email);

}
