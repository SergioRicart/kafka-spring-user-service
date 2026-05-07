package com.sergioricart.kafka_project.user.application.emailUpdated;

import com.sergioricart.kafka_project.common.application.CommandHandler;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.domain.event.UserVerificationRequestedDomainEvent;
import com.sergioricart.kafka_project.user.domain.port.UserEvent;
import com.sergioricart.kafka_project.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserEmailUpdatedHandler implements CommandHandler<UserEmailUpdatedCommand, VoidResponse> {

    private final UserRepository userRepository;

    private final UserEvent userEvent;

    @Override
    public VoidResponse handle(UserEmailUpdatedCommand userCommand) {

        log.info("UserEmailUpdatedHandler received command {}", userCommand);

        userRepository.findById(userCommand.getId()).ifPresent(user -> {

            user.setEmail(userCommand.getEmail());
            user.setUpdatedAt(userCommand.getTimestamp());

            log.info("UserEmailUpdatedHandler updated user {}", user);

            userRepository.save(user);

            UserVerificationRequestedDomainEvent userVerificationRequestedDomainEvent = UserVerificationRequestedDomainEvent.of(user);

            userEvent.sendUserVerificationRequestedEvent(userVerificationRequestedDomainEvent);

        });

        return new VoidResponse();

    }

    @Override
    public Class<UserEmailUpdatedCommand> getCommandType() {
        return UserEmailUpdatedCommand.class;
    }


}
