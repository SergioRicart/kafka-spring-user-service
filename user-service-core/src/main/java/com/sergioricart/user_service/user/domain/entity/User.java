package com.sergioricart.user_service.user.domain.entity;

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
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

}
