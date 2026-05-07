package com.sergioricart.kafka_project.user.application.http.created;

import com.sergioricart.kafka_project.common.application.CommandHandler;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.Role;
import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateUserHandler implements CommandHandler<CreateUserCommand, VoidResponse> {

    private final UserRepository userRepository;

    @Override
    public VoidResponse handle(CreateUserCommand userCommand) {

        log.info("CreateUserHandler received command {}", userCommand);

        User user = User.builder()
                .firstName(userCommand.getFirstName())
                .lastName(userCommand.getLastName())
                .email(userCommand.getEmail())
                .role(Role.USER)
                .password(userCommand.getPassword())
                .createdAt(Instant.now())
                .build();

        userRepository.save(user);

        return new VoidResponse();

    }

    @Override
    public Class<CreateUserCommand> getCommandType() {
        return CreateUserCommand.class;
    }

}
