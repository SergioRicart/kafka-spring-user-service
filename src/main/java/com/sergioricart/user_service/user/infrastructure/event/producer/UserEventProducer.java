package com.sergioricart.user_service.user.infrastructure.event.producer;

import com.sergioricart.user_service.common.infrastructure.event.producer.KafkaProducer;
import com.sergioricart.user_service.user.domain.constant.UserConstants;
import com.sergioricart.user_service.user.domain.event.UserCreatedDomainEvent;
import com.sergioricart.user_service.user.domain.event.UserDeletedDomainEvent;
import com.sergioricart.user_service.user.domain.event.UserUpdatedDomainEvent;
import com.sergioricart.user_service.user.domain.port.UserEvent;
import com.sergioricart.user_service.user.infrastructure.event.mapper.UserEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventProducer implements UserEvent {

    private final KafkaProducer kafkaProducer;

    private final UserEventMapper userEventMapper;

    @Value(UserConstants.USER_TOPIC)
    private String topic;

    @Override
    public void sendUserCreatedEvent(UserCreatedDomainEvent event) {

        log.info("UserEventProducer sending user created event {}", event);

        kafkaProducer.send(topic, userEventMapper.mapToUserCreatedEvent(event));

    }

    @Override
    public void sendUserDeletedEvent(UserDeletedDomainEvent event) {

        log.info("UserEventProducer sending user deleted event {}", event);

        kafkaProducer.send(topic, userEventMapper.mapToUserDeletedEvent(event));

    }

    @Override
    public void sendUserUpdatedEvent(UserUpdatedDomainEvent event) {

        log.info("UserEventProducer sending user updated event {}", event);

        kafkaProducer.send(topic, userEventMapper.mapToUserUpdatedEvent(event));

    }

}
