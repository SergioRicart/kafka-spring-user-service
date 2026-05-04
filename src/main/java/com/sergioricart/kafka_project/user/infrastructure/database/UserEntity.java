package com.sergioricart.kafka_project.user.infrastructure.database;

import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserEntity {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private Instant timestamp;
    private String password;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;

}
