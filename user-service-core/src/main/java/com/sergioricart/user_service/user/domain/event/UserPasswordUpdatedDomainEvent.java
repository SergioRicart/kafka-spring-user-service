package com.sergioricart.user_service.user.domain.event;

import com.sergioricart.user_service.user.domain.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserPasswordUpdatedDomainEvent {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Long timestamp;

    public static UserPasswordUpdatedDomainEvent of(User user) {
        return UserPasswordUpdatedDomainEvent.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

}
