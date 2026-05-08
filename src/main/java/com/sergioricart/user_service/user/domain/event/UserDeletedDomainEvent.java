package com.sergioricart.user_service.user.domain.event;

import com.sergioricart.user_service.user.domain.entiry.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserDeletedDomainEvent {

    private String id;
    private Long timestamp;

    public static UserDeletedDomainEvent of(User user) {
        return UserDeletedDomainEvent.builder()
                .id(user.getId())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

}
