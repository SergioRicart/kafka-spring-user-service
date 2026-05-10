/**
 * Handler que procesa eventos de creación/borrado de usuarios llegados
 * por Kafka desde un servicio externo.
 * No confundir con CreateUserHandler (http), que procesa peticiones HTTP
 * directas y además publica el evento de salida a "user.out".
 */

/*
package com.sergioricart.user_service.user.application.kafka.deleted;

import com.sergioricart.commons.application.CommandHandler;
import com.sergioricart.commons.application.VoidResponse;
import com.sergioricart.user_service.user.domain.port.UserEvent;
import com.sergioricart.user_service.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        userRepository.findById(userCommand.getId()).ifPresent(user -> {

                    user.setDeletedAt(userCommand.getTimestamp());

                    log.info("UserDeletedHandler deleted user {}", user);

                    userRepository.save(user);

*/
/*                    UserDeactivatedDomainEvent userDeactivatedDomainEvent = UserDeactivatedDomainEvent.of(user);

                    userEvent.sendUserDeactivatedEvent(userDeactivatedDomainEvent);*//*

        });


        return new VoidResponse();

    }

    @Override
    public Class<UserDeletedCommand> getCommandType() {
        return UserDeletedCommand.class;
    }


}
*/
