package com.sergioricart.kafka_project.user.application.http.delete;

import com.sergioricart.kafka_project.common.application.CommandHandler;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.constant.UserConstants;
import com.sergioricart.kafka_project.user.domain.event.UserDeletedDomainEvent;
import com.sergioricart.kafka_project.user.domain.exception.UserNotFoundException;
import com.sergioricart.kafka_project.user.domain.port.UserEvent;
import com.sergioricart.kafka_project.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeleteUserHandler implements CommandHandler<DeleteUserCommand, VoidResponse> {

    private final UserRepository userRepository;

    private final UserEvent userEvent;

    @Override
    @Transactional
    public VoidResponse handle(DeleteUserCommand userCommand) {

        log.info("DeleteUserHandler received command {}", userCommand);

        userRepository.findById(userCommand.getId()).ifPresentOrElse(user -> {

            user.setDeletedAt(Instant.now());

            userRepository.save(user);

            userEvent.sendUserDeletedEvent(UserDeletedDomainEvent.of(user));

        }, () -> {
            throw new UserNotFoundException(UserConstants.USER_NOT_FOUND_MESSAGE);
        });

        return new VoidResponse();

    }

    @Override
    public Class<DeleteUserCommand> getCommandType() {
        return DeleteUserCommand.class;
    }

}
