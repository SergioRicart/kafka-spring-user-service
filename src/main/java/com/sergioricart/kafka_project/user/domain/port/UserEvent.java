package com.sergioricart.kafka_project.user.domain.port;

import com.sergioricart.kafka_project.user.domain.event.UserDeactivatedDomainEvent;
import com.sergioricart.kafka_project.user.domain.event.UserVerificationRequestedDomainEvent;

public interface UserEvent {

    void sendUserDeactivatedEvent(UserDeactivatedDomainEvent userDeactivatedDomainEvenet);

    void sendUserVerificationRequestedEvent(UserVerificationRequestedDomainEvent userVerificationRequestedDomainEvent);

}
