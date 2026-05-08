/**
 * Handler que procesa eventos de creación/borrado de usuarios llegados
 * por Kafka desde un servicio externo.
 * No confundir con CreateUserHandler (http), que procesa peticiones HTTP
 * directas y además publica el evento de salida a "user.out".
 */


/*
package com.sergioricart.user_service.user.application.kafka.updated;

import com.sergioricart.user_service.common.application.CommandHandler;
import com.sergioricart.user_service.common.application.VoidResponse;
import com.sergioricart.user_service.user.domain.entiry.User;
import com.sergioricart.user_service.user.domain.port.UserEvent;
import com.sergioricart.user_service.user.domain.port.UserRepository;
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
*/
