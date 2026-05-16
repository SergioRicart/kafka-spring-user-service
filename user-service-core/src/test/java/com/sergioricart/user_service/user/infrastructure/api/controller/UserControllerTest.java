package com.sergioricart.user_service.user.infrastructure.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergioricart.commons.application.Mediator;
import com.sergioricart.commons.application.VoidResponse;
import com.sergioricart.user_service.fixtures.UserFixture;
import com.sergioricart.user_service.user.application.http.delete.DeleteUserCommand;
import com.sergioricart.user_service.user.application.http.get.GetUserByIdQuery;
import com.sergioricart.user_service.user.application.http.getAll.GetAllUsersQuery;
import com.sergioricart.user_service.user.application.http.update.UpdateUserCommand;
import com.sergioricart.user_service.user.domain.constant.UserConstants;
import com.sergioricart.user_service.user.domain.exception.UserNotFoundException;
import com.sergioricart.user_service.user.infrastructure.api.contoller.UserController;
import com.sergioricart.user_service.user.infrastructure.api.dto.request.UserCreatedRequest;
import com.sergioricart.user_service.user.infrastructure.api.mapper.UserApiMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private Mediator mediator;

    @MockitoBean
    private UserApiMapper userApiMapper;

    @Autowired
    private ObjectMapper objectMapper;

    // ──────────────── POST /api/v1/users/create ────────────────

    @Test
    void createUser_givenValidRequest_returns201() throws Exception {
        when(userApiMapper.mapToCreateUserCommand(any())).thenReturn(UserFixture.aCreateUserCommand());
        when(mediator.dispatch(any())).thenReturn(new VoidResponse());

        mockMvc.perform(post(UserFixture.CREATE_USER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserFixture.aUserCreatedRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    void createUser_givenMissingFirstName_returns400() throws Exception {
        UserCreatedRequest request = new UserCreatedRequest(null, UserFixture.LAST_NAME, UserFixture.ROLE,  UserFixture.EMAIL, UserFixture.PASSWORD);

        mockMvc.perform(post(UserFixture.CREATE_USER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUser_givenBlankEmail_returns400() throws Exception {
        UserCreatedRequest request = new UserCreatedRequest(UserFixture.FIRST_NAME, UserFixture.LAST_NAME, UserFixture.ROLE, "", UserFixture.PASSWORD);

        mockMvc.perform(post(UserFixture.CREATE_USER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUser_givenMissingPassword_returns400() throws Exception {
        UserCreatedRequest request = new UserCreatedRequest(UserFixture.FIRST_NAME, UserFixture.LAST_NAME, UserFixture.ROLE, UserFixture.EMAIL, null);

        mockMvc.perform(post(UserFixture.CREATE_USER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    // ──────────────── DELETE /api/v1/users/delete/{id} ────────────────

    @Test
    void deleteUser_givenValidId_returns204() throws Exception {
        when(mediator.dispatch(any(DeleteUserCommand.class))).thenReturn(new VoidResponse());

        mockMvc.perform(delete(UserFixture.DELETE_USER_PATH + UserFixture.USER_ID))
                .andExpect(status().isNoContent());

        verify(mediator).dispatch(any(DeleteUserCommand.class));
    }

    @Test
    void deleteUser_givenNonExistingUser_propagatesUserNotFoundException() {
        when(mediator.dispatch(any(DeleteUserCommand.class)))
                .thenThrow(new UserNotFoundException(UserConstants.USER_NOT_FOUND_MESSAGE));

        assertThatThrownBy(() ->
                mockMvc.perform(delete(UserFixture.DELETE_USER_PATH + UserFixture.UNKNOWN_ID)))
                .hasCauseInstanceOf(UserNotFoundException.class);
    }

    // ──────────────── PATCH /api/v1/users/update/{id} ────────────────

    @Test
    void updateUser_givenValidRequest_returns204() throws Exception {
        when(userApiMapper.mapToUpdateUserCommand(any())).thenReturn(UserFixture.aPartialUpdateUserCommand());
        when(mediator.dispatch(any(UpdateUserCommand.class))).thenReturn(new VoidResponse());

        mockMvc.perform(patch(UserFixture.UPDATE_USER_PATH + UserFixture.USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserFixture.aPartialUserUpdatedRequest())))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateUser_givenNonExistingUser_propagatesUserNotFoundException() throws Exception {
        when(userApiMapper.mapToUpdateUserCommand(any())).thenReturn(UserFixture.aPartialUpdateUserCommand());
        when(mediator.dispatch(any(UpdateUserCommand.class)))
                .thenThrow(new UserNotFoundException(UserConstants.USER_NOT_FOUND_MESSAGE));

        assertThatThrownBy(() ->
                mockMvc.perform(patch(UserFixture.UPDATE_USER_PATH + UserFixture.UNKNOWN_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserFixture.aPartialUserUpdatedRequest()))))
                .hasCauseInstanceOf(UserNotFoundException.class);
    }

    // ──────────────── GET /api/v1/users ────────────────

    @Test
    void getAllUsers_returns200WithUserList() throws Exception {
        when(mediator.dispatch(any(GetAllUsersQuery.class))).thenReturn(new PageImpl<>(List.of(UserFixture.aUser())));
        when(userApiMapper.mapToUserResponse(any())).thenReturn(UserFixture.aUserResponse());

        mockMvc.perform(get(UserFixture.GET_ALL_USERS_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(UserFixture.USER_ID))
                .andExpect(jsonPath("$.content[0].firstName").value(UserFixture.FIRST_NAME));
    }

    @Test
    void getAllUsers_whenEmpty_returns200WithEmptyList() throws Exception {
        when(mediator.dispatch(any(GetAllUsersQuery.class))).thenReturn(new PageImpl<>(List.of()));

        mockMvc.perform(get(UserFixture.GET_ALL_USERS_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isEmpty());
    }

    // ──────────────── GET /api/v1/users/{id} ────────────────

    @Test
    void getUserById_givenExistingId_returns200WithUser() throws Exception {
        when(mediator.dispatch(any(GetUserByIdQuery.class))).thenReturn(UserFixture.aUser());
        when(userApiMapper.mapToUserResponse(any())).thenReturn(UserFixture.aUserResponse());

        mockMvc.perform(get(UserFixture.GET_USER_BY_ID_PATH + UserFixture.USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(UserFixture.USER_ID))
                .andExpect(jsonPath("$.email").value(UserFixture.EMAIL));
    }

    @Test
    void getUserById_givenNonExistingId_propagatesUserNotFoundException() {
        when(mediator.dispatch(any(GetUserByIdQuery.class)))
                .thenThrow(new UserNotFoundException(UserConstants.USER_NOT_FOUND_MESSAGE));

        assertThatThrownBy(() ->
                mockMvc.perform(get(UserFixture.GET_USER_BY_ID_PATH + UserFixture.UNKNOWN_ID)))
                .hasCauseInstanceOf(UserNotFoundException.class);
    }
}
