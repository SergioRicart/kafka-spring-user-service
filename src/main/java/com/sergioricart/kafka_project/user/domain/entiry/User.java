package com.sergioricart.kafka_project.user.domain.entiry;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class User {

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
