package com.sergioricart.kafka_project.user.domain.port;

import com.sergioricart.kafka_project.user.domain.event.UserCreatedDomainEvent;
import com.sergioricart.kafka_project.user.domain.event.UserDeactivatedDomainEvent;
import com.sergioricart.kafka_project.user.domain.event.UserDeletedDomainEvent;
import com.sergioricart.kafka_project.user.domain.event.UserVerificationRequestedDomainEvent;

public interface UserEvent {

    void sendUserCreatedEvent(UserCreatedDomainEvent event);

    void sendUserDeletedEvent(UserDeletedDomainEvent event);

    void sendUserDeactivatedEvent(UserDeactivatedDomainEvent userDeactivatedDomainEvenet);

    void sendUserVerificationRequestedEvent(UserVerificationRequestedDomainEvent userVerificationRequestedDomainEvent);

}
