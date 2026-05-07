package com.sergioricart.kafka_project.user.domain.event;

import com.sergioricart.kafka_project.user.domain.entiry.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserUpdatedDomainEvent {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String password;
    private Long timestamp;

    public static UserUpdatedDomainEvent of(User user) {
        return UserUpdatedDomainEvent.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .password(user.getPassword())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

}
