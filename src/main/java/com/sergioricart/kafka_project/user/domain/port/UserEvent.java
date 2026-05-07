package com.sergioricart.kafka_project.user.domain.port;

import com.sergioricart.kafka_project.user.domain.event.UserCreatedDomainEvent;
import com.sergioricart.kafka_project.user.domain.event.UserDeletedDomainEvent;

public interface UserEvent {

    void sendUserCreatedEvent(UserCreatedDomainEvent event);

    void sendUserDeletedEvent(UserDeletedDomainEvent event);

}
