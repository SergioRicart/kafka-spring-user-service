package com.sergioricart.kafka_project.user.infrastructure.api.contoller;

import com.sergioricart.kafka_project.common.application.Mediator;
import com.sergioricart.kafka_project.user.application.http.created.CreateUserCommand;
import com.sergioricart.kafka_project.user.application.http.deleted.DeleteUserCommand;
import com.sergioricart.kafka_project.user.domain.constant.UserConstants;
import com.sergioricart.kafka_project.user.infrastructure.api.dto.request.UserCreatedRequest;
import com.sergioricart.kafka_project.user.infrastructure.event.mapper.UserEventMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final Mediator mediator;

    private final UserEventMapper eventMapper;


    @Value(UserConstants.USER_TOPIC)
    private String topic;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreatedRequest userDto) {

        log.debug("Creating user {}", userDto);

        CreateUserCommand command = eventMapper.mapToCreateUserCommand(userDto);

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

/*    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody UserUpdatedRequest userDto) {

        log.info(userDto.toString());


        User existing = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        UserUpdatedEvent userUpdatedEvent = UserUpdatedEvent.newBuilder()
                .setId(id)
                .setFirstName(userDto.getFirstName().ifPresent(event::setFirstName))
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .setRole(userDto.getRole().toString())
                .setPassword(userDto.getPassword())
                .setTimestamp(System.currentTimeMillis())
                .build();

        kafkaProducer.send(topic, userUpdatedEvent);

        return  ResponseEntity.ok().build();

    }*/


}
