package com.sergioricart.kafka_project.user.infrastructure.database;

import com.sergioricart.kafka_project.user.domain.entiry.Role;
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
