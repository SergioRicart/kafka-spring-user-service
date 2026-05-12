package com.sergioricart.user_service.user.domain.port;

import com.sergioricart.user_service.user.domain.event.UserCreatedDomainEvent;
import com.sergioricart.user_service.user.domain.event.UserDeletedDomainEvent;
import com.sergioricart.user_service.user.domain.event.UserPasswordUpdatedDomainEvent;
import com.sergioricart.user_service.user.domain.event.UserUpdatedDomainEvent;

public interface UserEvent {

    void sendUserCreatedEvent(UserCreatedDomainEvent event);

    void sendUserDeletedEvent(UserDeletedDomainEvent event);

    void sendUserUpdatedEvent(UserUpdatedDomainEvent event);

    void sendUserPasswordUpdatedEvent(UserPasswordUpdatedDomainEvent event);

}
