package com.sergioricart.kafka_project.user.application.http.deleted;

import com.sergioricart.kafka_project.common.application.CommandHandler;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeleteUserHandler implements CommandHandler<DeleteUserCommand, VoidResponse> {

    private final UserRepository userRepository;

    @Override
    public VoidResponse handle(DeleteUserCommand userCommand) {

        log.info("UserDeletedHandler received command {}", userCommand);

        userRepository.findById(userCommand.getId()).ifPresent(user -> {

            user.setDeletedAt(Instant.now());

            userRepository.save(user);

        });


        return new VoidResponse();

    }

    @Override
    public Class<DeleteUserCommand> getCommandType() {
        return DeleteUserCommand.class;
    }


}
