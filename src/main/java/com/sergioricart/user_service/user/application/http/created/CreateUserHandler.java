package com.sergioricart.user_service.user.application.http.created;

import com.sergioricart.user_service.common.application.CommandHandler;
import com.sergioricart.user_service.common.application.VoidResponse;
import com.sergioricart.user_service.user.domain.entiry.Role;
import com.sergioricart.user_service.user.domain.entiry.User;
import com.sergioricart.user_service.user.domain.event.UserCreatedDomainEvent;
import com.sergioricart.user_service.user.domain.port.UserEvent;
import com.sergioricart.user_service.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateUserHandler implements CommandHandler<CreateUserCommand, VoidResponse> {

    private final UserRepository userRepository;

    private final UserEvent userEvent;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public VoidResponse handle(CreateUserCommand userCommand) {

        log.info("CreateUserHandler received command {}", userCommand);

        User user = User.builder()
                .firstName(userCommand.getFirstName())
                .lastName(userCommand.getLastName())
                .email(userCommand.getEmail())
                .role(Role.USER)
                .password(passwordEncoder.encode(userCommand.getPassword()))
                .createdAt(Instant.now())
                .build();

        userRepository.save(user);

        userEvent.sendUserCreatedEvent(UserCreatedDomainEvent.of(user));

        return new VoidResponse();

    }

    @Override
    public Class<CreateUserCommand> getCommandType() {
        return CreateUserCommand.class;
    }

}
