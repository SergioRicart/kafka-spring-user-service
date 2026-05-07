package com.sergioricart.kafka_project.user.application.kafka.updated;

import com.sergioricart.kafka_project.common.application.CommandHandler;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.domain.port.UserEvent;
import com.sergioricart.kafka_project.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserUpdatedHandler implements CommandHandler<UserUpdatedCommand, VoidResponse> {

    private final UserRepository userRepository;

    private final UserEvent userEvent;

    @Override
    public VoidResponse handle(UserUpdatedCommand userCommand) {

        log.info("UserCreatedHandler received command {}", userCommand);

        User user = User.builder()
                .id(userCommand.getId())
                .firstName(userCommand.getFirstName())
                .lastName(userCommand.getLastName())
                .email(userCommand.getEmail())
                .role(userCommand.getRole())
                .password(userCommand.getPassword())
                .updatedAt(userCommand.getTimestamp())
                .updatedAt(null)
                .deletedAt(null)
                .build();

        userRepository.save(user);

        return new VoidResponse();

    }

    @Override
    public Class<UserUpdatedCommand> getCommandType() {
        return UserUpdatedCommand.class;
    }


}
