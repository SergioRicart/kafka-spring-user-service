package com.sergioricart.user_service.user.infrastructure.api.contoller;

import com.sergioricart.commons.application.Mediator;
import com.sergioricart.user_service.user.application.http.created.CreateUserCommand;
import com.sergioricart.user_service.user.application.http.delete.DeleteUserCommand;
import com.sergioricart.user_service.user.application.http.get.GetUserByIdQuery;
import com.sergioricart.user_service.user.application.http.getAll.GetAllUsersQuery;
import com.sergioricart.user_service.user.application.http.update.UpdateUserCommand;
import com.sergioricart.user_service.user.domain.entity.User;
import com.sergioricart.user_service.user.infrastructure.api.dto.request.UserCreatedRequest;
import com.sergioricart.user_service.user.infrastructure.api.dto.request.UserUpdatedRequest;
import com.sergioricart.user_service.user.infrastructure.api.dto.response.UserResponse;
import com.sergioricart.user_service.user.infrastructure.api.mapper.UserApiMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final Mediator mediator;

    private final UserApiMapper apiMapper;

/*
    @PreAuthorize("hasRole('ADMIN')")
*/
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreatedRequest userDto) {

        log.debug("Creating user {}", userDto);

        CreateUserCommand command = apiMapper.mapToCreateUserCommand(userDto);

        mediator.dispatch(command);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

/*
    @PreAuthorize("hasRole('ADMIN')")
*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {

        DeleteUserCommand command = new DeleteUserCommand();
        command.setId(id);

        mediator.dispatch(command);

        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/update/{id}")
    public  ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody UserUpdatedRequest userDto) {

        UpdateUserCommand command = apiMapper.mapToUpdateUserCommand(userDto);

        command.setId(id);

        mediator.dispatch(command);

        return  ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String q) {

        GetAllUsersQuery query = new GetAllUsersQuery();
        query.setPage(page);
        query.setSize(size);
        query.setQ(q);

        Page<User> users = mediator.dispatch(query);

        return ResponseEntity.ok(users.map(apiMapper::mapToUserResponse));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {

        User user = mediator.dispatch(new GetUserByIdQuery(id));

        return ResponseEntity.ok(apiMapper.mapToUserResponse(user));

    }

}
