package com.sergioricart.kafkaSpringUserService.user.domain.event;

import com.sergioricart.kafkaSpringUserService.user.domain.entiry.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserCreatedDomainEvent {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private Long timestamp;

    public static UserCreatedDomainEvent of(User user) {
        return UserCreatedDomainEvent.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

}
