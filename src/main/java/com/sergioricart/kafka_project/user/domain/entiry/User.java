package com.sergioricart.kafka_project.user.domain.entiry;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    @Transient
    private Instant timestamp;

    private String password;

    Instant createdAt;

    Instant updatedAt;

    Instant deletedAt;

}
