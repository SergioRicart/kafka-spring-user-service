package com.sergioricart.kafka_project.user.application;

import com.sergioricart.kafka_project.common.application.CommandHandler;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserCreatedHandler implements CommandHandler<UserCreatedCommand, VoidResponse> {

    private final UserRepository userRepository;

    @Override
    public VoidResponse handle(UserCreatedCommand command) {

        log.info("UserCreatedHandler received command {}", command);

        User user = User.builder()
                .id(command.getId())
                .firstname(command.getFirstname())
                .lastname(command.getLastname())
                .email(command.getEmail())
                .role(command.getRole())
                .password(RandomStringUtils.randomAlphanumeric(10))
                .createdAt(command.getTimestamp())
                .updatedAt(null)
                .deletedAt(null)
                .build();

        userRepository.save(user);

        return new VoidResponse();

    }

    @Override
    public Class<UserCreatedCommand> getCommandType() {
        return UserCreatedCommand.class;
    }


}
