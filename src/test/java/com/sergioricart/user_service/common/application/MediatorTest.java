package com.sergioricart.user_service.common.application;

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
        // ARRANGE: creamos un handler mock que declara manejar CreateUserCommand
        CommandHandler<CreateUserCommand, VoidResponse> handler = mock(CommandHandler.class);
        when(handler.getCommandType()).thenReturn(CreateUserCommand.class);

        CreateUserCommand command = new CreateUserCommand();
        VoidResponse respuestaEsperada = new VoidResponse();
        when(handler.handle(command)).thenReturn(respuestaEsperada);

        // El Mediator recibe la lista de handlers en el constructor y los indexa por tipo
        Mediator mediator = new Mediator(List.of(handler));

        // ACT
        VoidResponse resultado = mediator.dispatch(command);

        // ASSERT
        assertThat(resultado).isSameAs(respuestaEsperada);
        verify(handler).handle(command);
    }

    @Test
    @SuppressWarnings("unchecked")
    void dispatch_givenMultipleHandlers_routesToTheCorrectOne() {
        // Con varios handlers registrados, el Mediator debe enrutar al correcto
        CommandHandler<CreateUserCommand, VoidResponse> createHandler = mock(CommandHandler.class);
        when(createHandler.getCommandType()).thenReturn(CreateUserCommand.class);

        CommandHandler<DeleteUserCommand, VoidResponse> deleteHandler = mock(CommandHandler.class);
        when(deleteHandler.getCommandType()).thenReturn(DeleteUserCommand.class);

        Mediator mediator = new Mediator(List.of(createHandler, deleteHandler));

        DeleteUserCommand deleteCommand = new DeleteUserCommand();
        mediator.dispatch(deleteCommand);

        // Solo debe haberse invocado el handler de delete
        verify(deleteHandler).handle(deleteCommand);
        // El constructor ya llamó getCommandType() en createHandler para indexarlo,
        // así que usamos never() sobre handle() en lugar de verifyNoInteractions()
        verify(createHandler, never()).handle(any());
    }

    @Test
    void dispatch_givenUnregisteredCommand_throwsRuntimeException() {
        // Si no hay ningún handler para el command, el Mediator lanza RuntimeException
        Mediator mediator = new Mediator(List.of());

        CreateUserCommand command = new CreateUserCommand();

        assertThatThrownBy(() -> mediator.dispatch(command))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("No handlerFound");
    }
}
