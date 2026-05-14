package com.sergioricart.user_service.user.domain.event;

import com.sergioricart.user_service.user.domain.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserDeletedDomainEvent {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Long timestamp;

    public static UserDeletedDomainEvent of(User user) {
        return UserDeletedDomainEvent.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

}
