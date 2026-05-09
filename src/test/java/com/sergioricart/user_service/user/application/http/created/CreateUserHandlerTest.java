package com.sergioricart.user_service.user.application.http.created;

import com.sergioricart.commons.application.VoidResponse;
import com.sergioricart.user_service.fixtures.UserFixture;
import com.sergioricart.user_service.user.domain.entiry.Role;
import com.sergioricart.user_service.user.domain.entiry.User;
import com.sergioricart.user_service.user.domain.event.UserCreatedDomainEvent;
import com.sergioricart.user_service.user.domain.port.UserEvent;
import com.sergioricart.user_service.user.domain.port.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class CreateUserHandlerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEvent userEvent;

    @InjectMocks
    private CreateUserHandler handler;

    @Test
    void handle_givenValidCommand_savesUserWithCorrectData() {
        handler.handle(UserFixture.aCreateUserCommand());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User userGuardado = userCaptor.getValue();
        assertThat(userGuardado.getFirstName()).isEqualTo(UserFixture.FIRST_NAME);
        assertThat(userGuardado.getLastName()).isEqualTo(UserFixture.LAST_NAME);
        assertThat(userGuardado.getEmail()).isEqualTo(UserFixture.EMAIL);
        assertThat(userGuardado.getPassword()).isEqualTo(UserFixture.PASSWORD);
        assertThat(userGuardado.getRole()).isEqualTo(Role.USER);
        assertThat(userGuardado.getCreatedAt()).isNotNull();
    }

    @Test
    void handle_givenValidCommand_publishesUserCreatedEvent() {
        handler.handle(UserFixture.aCreateUserCommand());

        verify(userEvent).sendUserCreatedEvent(any(UserCreatedDomainEvent.class));
        verifyNoMoreInteractions(userEvent);
    }

    @Test
    void handle_givenValidCommand_returnsVoidResponse() {
        VoidResponse result = handler.handle(UserFixture.aCreateUserCommand());

        assertThat(result).isNotNull();
    }

    @Test
    void getCommandType_returnsCreateUserCommandClass() {
        assertThat(handler.getCommandType()).isEqualTo(CreateUserCommand.class);
    }
}
