package com.sergioricart.user_service.user.infrastructure.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String roleId;

    @Transient
    private Instant timestamp;

    private String password;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

}
