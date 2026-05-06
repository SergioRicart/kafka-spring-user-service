package com.sergioricart.kafka_project.user.infrastructure.api.dto.response;

import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserResponse {

    String id;

    String firstName;

    String lastName;

    String email;

    Role role;

    String password;

    Instant createdAt;

    Instant updatedAt;

    Instant deletedAt;

}
