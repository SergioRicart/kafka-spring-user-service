package com.sergioricart.kafka_project.user.infrastructure.database;

import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserEntity {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Instant timestamp;
    private String password;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;

}
