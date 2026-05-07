package com.sergioricart.kafka_project.user.application.kafka.created;

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
public class UserCreatedHandler implements CommandHandler<UserCreatedCommand, VoidResponse> {

    private final UserRepository userRepository;

    private final UserEvent userEvent;

    @Override
    public VoidResponse handle(UserCreatedCommand userCommand) {

        log.info("UserCreatedHandler received command {}", userCommand);

        User user = User.builder()
                .firstName(userCommand.getFirstName())
                .lastName(userCommand.getLastName())
                .email(userCommand.getEmail())
                .role(userCommand.getRole())
                .password(userCommand.getPassword())
                .createdAt(userCommand.getTimestamp())
                .updatedAt(null)
                .deletedAt(null)
                .build();

        userRepository.save(user);

/*        UserVerificationRequestedDomainEvent userVerificationRequestedDomainEvent = UserVerificationRequestedDomainEvent.of(user);

        userEvent.sendUserVerificationRequestedEvent(userVerificationRequestedDomainEvent);*/

        return new VoidResponse();

    }

    @Override
    public Class<UserCreatedCommand> getCommandType() {
        return UserCreatedCommand.class;
    }


}
