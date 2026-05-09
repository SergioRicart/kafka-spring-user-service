package com.sergioricart.user_service.user.application;

import com.sergioricart.commons.application.CommandHandler;
import com.sergioricart.commons.application.Mediator;
import com.sergioricart.commons.application.VoidResponse;
import com.sergioricart.user_service.user.application.http.created.CreateUserCommand;
import com.sergioricart.user_service.user.application.http.delete.DeleteUserCommand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class MediatorTest {

    @Test
    @SuppressWarnings("unchecked")
    void dispatch_givenRegisteredCommand_invokesCorrectHandler() {
        CommandHandler<CreateUserCommand, VoidResponse> handler = mock(CommandHandler.class);
        when(handler.getCommandType()).thenReturn(CreateUserCommand.class);

        CreateUserCommand command = new CreateUserCommand();
        VoidResponse expected = new VoidResponse();
        when(handler.handle(command)).thenReturn(expected);

        Mediator mediator = new Mediator(List.of(handler));

        VoidResponse result = mediator.dispatch(command);

        assertThat(result).isSameAs(expected);
        verify(handler).handle(command);
    }

    @Test
    @SuppressWarnings("unchecked")
    void dispatch_givenMultipleHandlers_routesToTheCorrectOne() {
        CommandHandler<CreateUserCommand, VoidResponse> createHandler = mock(CommandHandler.class);
        when(createHandler.getCommandType()).thenReturn(CreateUserCommand.class);

        CommandHandler<DeleteUserCommand, VoidResponse> deleteHandler = mock(CommandHandler.class);
        when(deleteHandler.getCommandType()).thenReturn(DeleteUserCommand.class);

        Mediator mediator = new Mediator(List.of(createHandler, deleteHandler));

        DeleteUserCommand deleteCommand = new DeleteUserCommand();
        mediator.dispatch(deleteCommand);

        verify(deleteHandler).handle(deleteCommand);
        verify(createHandler, never()).handle(any());
    }

    @Test
    void dispatch_givenUnregisteredCommand_throwsRuntimeException() {
        Mediator mediator = new Mediator(List.of());

        assertThatThrownBy(() -> mediator.dispatch(new CreateUserCommand()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("No handler found");
    }
}
