package com.sergioricart.user_service.user.application.http.update;

import com.sergioricart.commons.application.CommandHandler;
import com.sergioricart.commons.application.VoidResponse;
import com.sergioricart.user_service.user.domain.constant.UserConstants;
import com.sergioricart.user_service.user.domain.entiry.User;
import com.sergioricart.user_service.user.domain.event.UserUpdatedDomainEvent;
import com.sergioricart.user_service.user.domain.exception.UserNotFoundException;
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
public class UpdateUserHandler implements CommandHandler<UpdateUserCommand, VoidResponse> {

    private final UserRepository userRepository;

    private final UserEvent userEvent;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public VoidResponse handle(UpdateUserCommand userCommand) {

        log.info("UpdateUserHandler received command {}", userCommand);

        User userEntity = userRepository.findById(userCommand.getId()).orElseThrow(() ->
                new UserNotFoundException(UserConstants.USER_NOT_FOUND_MESSAGE)
        );

        userEntity.setId(userCommand.getId());

        if (userCommand.getFirstName() != null) userEntity.setFirstName(userCommand.getFirstName());
        if (userCommand.getLastName() != null) userEntity.setLastName(userCommand.getLastName());
        if (userCommand.getEmail() != null) userEntity.setEmail(userCommand.getEmail());
        if (userCommand.getRole() != null) userEntity.setRole(userCommand.getRole());
        if (userCommand.getPassword() != null) userEntity.setPassword(passwordEncoder.encode(userCommand.getPassword()));

        userEntity.setUpdatedAt(Instant.now());

        userRepository.update(userEntity);

        userEvent.sendUserUpdatedEvent(UserUpdatedDomainEvent.of(userEntity));

        return new VoidResponse();

    }

    @Override
    public Class<UpdateUserCommand> getCommandType() {
        return UpdateUserCommand.class;
    }

}
