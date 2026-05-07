package com.sergioricart.kafka_project.user.infrastructure.api.contoller;

import com.sergioricart.kafka_project.common.application.Mediator;
import com.sergioricart.kafka_project.user.application.http.created.CreateUserCommand;
import com.sergioricart.kafka_project.user.application.http.deleted.DeleteUserCommand;
import com.sergioricart.kafka_project.user.infrastructure.api.dto.request.UserCreatedRequest;
import com.sergioricart.kafka_project.user.infrastructure.api.mapper.UserApiMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final Mediator mediator;

    private final UserApiMapper apiMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreatedRequest userDto) {

        log.debug("Creating user {}", userDto);

        CreateUserCommand command = apiMapper.mapToCreateUserCommand(userDto);

        mediator.dispatch(command);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {

        DeleteUserCommand command = new DeleteUserCommand();
        command.setId(id);

        mediator.dispatch(command);

        return ResponseEntity.noContent().build();

    }

}
