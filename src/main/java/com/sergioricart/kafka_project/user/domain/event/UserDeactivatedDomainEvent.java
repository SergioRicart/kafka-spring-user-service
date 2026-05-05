package com.sergioricart.kafka_project.user.domain.event;

import com.sergioricart.kafka_project.user.domain.entiry.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserDeactivatedDomainEvent {

    private String id;
    private Long timestamp;


    public static UserDeactivatedDomainEvent of(User user){

        return  UserDeactivatedDomainEvent.builder()
                .id(user.getId())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

}
