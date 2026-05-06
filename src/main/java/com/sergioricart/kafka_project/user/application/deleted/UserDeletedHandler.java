package com.sergioricart.kafka_project.user.application.deleted;

import com.sergioricart.kafka_project.common.application.CommandHandler;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.domain.event.UserDeactivatedDomainEvent;
import com.sergioricart.kafka_project.user.domain.port.UserEvent;
import com.sergioricart.kafka_project.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserDeletedHandler implements CommandHandler<UserDeletedCommand, VoidResponse> {

    private final UserRepository userRepository;

    private final UserEvent userEvent;

    @Override
    public VoidResponse handle(UserDeletedCommand userCommand) {

        log.info("UserDeletedHandler received command {}", userCommand);

        userRepository.findById(
                userCommand.getId()).ifPresent(user -> {

                    user.setDeletedAt(userCommand.getTimestamp());

                    log.info("UserDeletedHandler deleted user {}", user);

                    userRepository.save(user);

/*                    UserDeactivatedDomainEvent userDeactivatedDomainEvent = UserDeactivatedDomainEvent.of(user);

                    userEvent.sendUserDeactivatedEvent(userDeactivatedDomainEvent);*/
        });

        return new VoidResponse();

    }

    @Override
    public Class<UserDeletedCommand> getCommandType() {
        return UserDeletedCommand.class;
    }


}
