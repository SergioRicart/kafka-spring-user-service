package com.sergioricart.user_service.user.application.http.update;

import com.sergioricart.user_service.fixtures.UserFixture;
import com.sergioricart.user_service.user.domain.constant.UserConstants;
import com.sergioricart.user_service.user.domain.entiry.User;
import com.sergioricart.user_service.user.domain.event.UserUpdatedDomainEvent;
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
class UpdateUserHandlerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEvent userEvent;

    @InjectMocks
    private UpdateUserHandler handler;

    @Test
    void handle_givenFullUpdate_updatesAllFields() {
        User usuario = UserFixture.aUser();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.of(usuario));

        handler.handle(UserFixture.aFullUpdateUserCommand());

        assertThat(usuario.getFirstName()).isEqualTo(UserFixture.UPDATED_FIRST_NAME);
        assertThat(usuario.getLastName()).isEqualTo(UserFixture.UPDATED_LAST_NAME);
        assertThat(usuario.getEmail()).isEqualTo(UserFixture.UPDATED_EMAIL);
        assertThat(usuario.getPassword()).isEqualTo(UserFixture.UPDATED_PASSWORD);
        assertThat(usuario.getRole()).isEqualTo(UserFixture.UPDATED_ROLE);
        assertThat(usuario.getUpdatedAt()).isNotNull();
    }

    @Test
    void handle_givenPartialUpdate_onlyUpdatesProvidedFields() {
        User usuario = UserFixture.aUser();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.of(usuario));

        handler.handle(UserFixture.aPartialUpdateUserCommand());

        assertThat(usuario.getFirstName()).isEqualTo(UserFixture.UPDATED_FIRST_NAME);
        // El resto no debe cambiar respecto al fixture original
        assertThat(usuario.getLastName()).isEqualTo(UserFixture.LAST_NAME);
        assertThat(usuario.getEmail()).isEqualTo(UserFixture.EMAIL);
        assertThat(usuario.getPassword()).isEqualTo(UserFixture.PASSWORD);
        assertThat(usuario.getRole()).isEqualTo(UserFixture.ROLE);
    }

    @Test
    void handle_givenValidUpdate_callsRepositoryUpdateAndPublishesEvent() {
        User usuario = UserFixture.aUser();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.of(usuario));

        handler.handle(UserFixture.aPartialUpdateUserCommand());

        verify(userRepository).update(usuario);
        verify(userRepository, never()).save(any());
        verify(userEvent).sendUserUpdatedEvent(any(UserUpdatedDomainEvent.class));
    }

    @Test
    void handle_givenNonExistingUser_throwsUserNotFoundException() {
        when(userRepository.findById(UserFixture.UNKNOWN_ID)).thenReturn(Optional.empty());

        UpdateUserCommand command = new UpdateUserCommand();
        command.setId(UserFixture.UNKNOWN_ID);

        assertThatThrownBy(() -> handler.handle(command))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(UserConstants.USER_NOT_FOUND_MESSAGE);

        verifyNoInteractions(userEvent);
        verify(userRepository, never()).update(any());
    }
}
