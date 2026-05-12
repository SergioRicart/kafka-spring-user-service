package com.sergioricart.user_service.user.application.http.delete;

import com.sergioricart.user_service.fixtures.UserFixture;
import com.sergioricart.user_service.user.domain.constant.UserConstants;
import com.sergioricart.user_service.user.domain.entity.User;
import com.sergioricart.user_service.user.domain.event.UserDeletedDomainEvent;
import com.sergioricart.user_service.user.domain.exception.UserNotFoundException;
import com.sergioricart.user_service.user.domain.port.UserEvent;
import com.sergioricart.user_service.user.domain.port.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserHandlerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEvent userEvent;

    @InjectMocks
    private DeleteUserHandler handler;

    @Test
    void handle_givenExistingUser_setsDeletedAt() {
        User usuario = UserFixture.aUser();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.of(usuario));

        handler.handle(UserFixture.aDeleteUserCommand());

        assertThat(usuario.getDeletedAt()).isNotNull();
    }

    @Test
    void handle_givenExistingUser_savesAndPublishesEvent() {
        User usuario = UserFixture.aUser();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.of(usuario));

        handler.handle(UserFixture.aDeleteUserCommand());

        verify(userRepository).save(usuario);
        verify(userEvent).sendUserDeletedEvent(any(UserDeletedDomainEvent.class));
    }

    @Test
    void handle_givenNonExistingUser_throwsUserNotFoundException() {
        when(userRepository.findById(UserFixture.UNKNOWN_ID)).thenReturn(Optional.empty());

        DeleteUserCommand command = new DeleteUserCommand();
        command.setId(UserFixture.UNKNOWN_ID);

        assertThatThrownBy(() -> handler.handle(command))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(UserConstants.USER_NOT_FOUND_MESSAGE);

        verifyNoInteractions(userEvent);
    }

    @Test
    void handle_givenNonExistingUser_neverSaves() {
        when(userRepository.findById(UserFixture.UNKNOWN_ID)).thenReturn(Optional.empty());

        DeleteUserCommand command = new DeleteUserCommand();
        command.setId(UserFixture.UNKNOWN_ID);

        assertThatThrownBy(() -> handler.handle(command))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository, never()).save(any());
    }
}
