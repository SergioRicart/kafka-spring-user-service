package com.sergioricart.user_service.user.domain.event;

import com.sergioricart.user_service.user.domain.entity.User;
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
    private Long timestamp;

    public static UserUpdatedDomainEvent of(User user) {
        return UserUpdatedDomainEvent.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRoleId())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

}
