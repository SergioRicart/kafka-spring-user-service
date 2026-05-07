package com.sergioricart.kafkaSpringUserService.user.domain.port;

import com.sergioricart.kafkaSpringUserService.user.domain.event.UserCreatedDomainEvent;
import com.sergioricart.kafkaSpringUserService.user.domain.event.UserDeletedDomainEvent;
import com.sergioricart.kafkaSpringUserService.user.domain.event.UserUpdatedDomainEvent;

public interface UserEvent {

    void sendUserCreatedEvent(UserCreatedDomainEvent event);

    void sendUserDeletedEvent(UserDeletedDomainEvent event);

    void sendUserUpdatedEvent(UserUpdatedDomainEvent event);

}
