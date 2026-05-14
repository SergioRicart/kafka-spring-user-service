package com.sergioricart.user_service.fixtures;

import com.sergioricart.user_service.user.application.http.created.CreateUserCommand;
import com.sergioricart.user_service.user.application.http.delete.DeleteUserCommand;
import com.sergioricart.user_service.user.application.http.update.UpdateUserCommand;
import com.sergioricart.user_service.user.domain.entity.User;
import com.sergioricart.user_service.user.infrastructure.api.dto.request.UserCreatedRequest;
import com.sergioricart.user_service.user.infrastructure.api.dto.request.UserUpdatedRequest;

public final class UserFixture {

    private UserFixture() {}

    // ── Valores base ──────────────────────────────────────────────────────────
    public static final String USER_ID        = "user-123";
    public static final String FIRST_NAME     = "Juan";
    public static final String LAST_NAME      = "García";
    public static final String EMAIL          = "juan@example.com";
    public static final String PASSWORD       = "secret123";
    public static final String ROLE           = "1";

    public static final String UPDATED_FIRST_NAME = "Carlos";
    public static final String UPDATED_LAST_NAME  = "Rodríguez";
    public static final String UPDATED_EMAIL      = "carlos@example.com";
    public static final String UPDATED_PASSWORD   = "newpass";
    public static final String UPDATED_ROLE       = "2";

    public static final String UNKNOWN_ID = "no-existe";

    // ── Rutas API ─────────────────────────────────────────────────────────────
    public static final String CREATE_USER_PATH = "/api/v1/users/create";
    public static final String DELETE_USER_PATH = "/api/v1/users/delete/";
    public static final String UPDATE_USER_PATH = "/api/v1/users/update/";

    // ── Objetos de dominio ────────────────────────────────────────────────────

    public static User aUser() {
        return User.builder()
                .id(USER_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .roleId(ROLE)
                .password(PASSWORD)
                .build();
    }

    // ── Commands ──────────────────────────────────────────────────────────────

    public static CreateUserCommand aCreateUserCommand() {
        CreateUserCommand command = new CreateUserCommand();
        command.setFirstName(FIRST_NAME);
        command.setLastName(LAST_NAME);
        command.setEmail(EMAIL);
        command.setRoleId(ROLE);
        command.setPassword(PASSWORD);
        return command;
    }

    public static DeleteUserCommand aDeleteUserCommand() {
        DeleteUserCommand command = new DeleteUserCommand();
        command.setId(USER_ID);
        return command;
    }

    public static UpdateUserCommand aFullUpdateUserCommand() {
        UpdateUserCommand command = new UpdateUserCommand();
        command.setId(USER_ID);
        command.setFirstName(UPDATED_FIRST_NAME);
        command.setLastName(UPDATED_LAST_NAME);
        command.setEmail(UPDATED_EMAIL);
        command.setPassword(UPDATED_PASSWORD);
        command.setRoleId(UPDATED_ROLE);
        return command;
    }

    public static UpdateUserCommand aPartialUpdateUserCommand() {
        UpdateUserCommand command = new UpdateUserCommand();
        command.setId(USER_ID);
        command.setFirstName(UPDATED_FIRST_NAME);
        return command;
    }

    public static UpdateUserCommand aPasswordOnlyUpdateCommand() {
        UpdateUserCommand command = new UpdateUserCommand();
        command.setId(USER_ID);
        command.setPassword(UPDATED_PASSWORD);
        return command;
    }

    public static UpdateUserCommand aNoOpUpdateUserCommand() {
        UpdateUserCommand command = new UpdateUserCommand();
        command.setId(USER_ID);
        command.setFirstName(FIRST_NAME);
        command.setLastName(LAST_NAME);
        command.setEmail(EMAIL);
        command.setRoleId(ROLE);
        return command;
    }

    // ── DTOs HTTP ─────────────────────────────────────────────────────────────

    public static UserCreatedRequest aUserCreatedRequest() {
        return new UserCreatedRequest(FIRST_NAME, LAST_NAME, ROLE, EMAIL, PASSWORD);
    }

    public static UserUpdatedRequest aPartialUserUpdatedRequest() {
        return new UserUpdatedRequest(UPDATED_FIRST_NAME, null, null, null, null, null);
    }
}
