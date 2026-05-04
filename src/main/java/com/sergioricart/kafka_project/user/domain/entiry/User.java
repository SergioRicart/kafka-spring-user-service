package com.sergioricart.kafka_project.user.domain.entiry;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class User {


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
