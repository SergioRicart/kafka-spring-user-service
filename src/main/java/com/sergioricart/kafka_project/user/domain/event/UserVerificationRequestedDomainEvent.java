package com.sergioricart.kafka_project.user.domain.event;

import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.infrastructure.api.dto.response.UserResponse;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserVerificationRequestedDomainEvent {

    private String id;
    private String email;
    private Long timestamp;

    public static UserVerificationRequestedDomainEvent of(User user) {
        return UserVerificationRequestedDomainEvent.builder()
                .id(user.getId())
                .email(user.getEmail())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

}
