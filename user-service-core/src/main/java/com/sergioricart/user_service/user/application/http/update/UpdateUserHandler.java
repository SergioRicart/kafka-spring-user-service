package com.sergioricart.user_service.user.application.http.update;

import com.sergioricart.commons.application.CommandHandler;
import com.sergioricart.commons.application.VoidResponse;
import com.sergioricart.user_service.user.domain.constant.UserConstants;
import com.sergioricart.user_service.user.domain.entity.User;
import com.sergioricart.user_service.user.domain.event.UserPasswordUpdatedDomainEvent;
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
import java.util.Objects;

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

        String originalFirstName = userEntity.getFirstName();
        String originalLastName  = userEntity.getLastName();
        String originalEmail     = userEntity.getEmail();
        String   originalRole      = userEntity.getRoleId();

        boolean passwordChanged = userCommand.getPassword() != null;

        boolean anyOtherValueChanged =
                isChanged(userCommand.getFirstName(), originalFirstName) ||
                isChanged(userCommand.getLastName(),  originalLastName)  ||
                isChanged(userCommand.getEmail(),     originalEmail)     ||
                isChanged(userCommand.getRoleId(),      originalRole);

        if (userCommand.getFirstName() != null) userEntity.setFirstName(userCommand.getFirstName());
        if (userCommand.getLastName()  != null) userEntity.setLastName(userCommand.getLastName());
        if (userCommand.getEmail()     != null) userEntity.setEmail(userCommand.getEmail());
        if (userCommand.getRoleId()      != null) userEntity.setRoleId(userCommand.getRoleId());
        if (passwordChanged) userEntity.setPassword(passwordEncoder.encode(userCommand.getPassword()));

        userEntity.setUpdatedAt(Instant.now());

        userRepository.update(userEntity);

        if (passwordChanged) {
            userEvent.sendUserPasswordUpdatedEvent(UserPasswordUpdatedDomainEvent.of(userEntity));
        }

        if (anyOtherValueChanged) {
            userEvent.sendUserUpdatedEvent(UserUpdatedDomainEvent.of(userEntity));
        }

        return new VoidResponse();

    }

    @Override
    public Class<UpdateUserCommand> getCommandType() {
        return UpdateUserCommand.class;
    }

    private static <T> boolean isChanged(T incoming, T current) {
        return incoming != null && !Objects.equals(incoming, current);
    }

}
