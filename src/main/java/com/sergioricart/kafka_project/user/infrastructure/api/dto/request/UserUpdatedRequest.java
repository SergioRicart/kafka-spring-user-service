package com.sergioricart.kafka_project.user.infrastructure.api.dto.request;

import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatedRequest {

     Optional<String> firstName;

     Optional<String> lastName;

     Optional<String> email;

     Optional<Role> role;

     Optional<Instant> timestamp;

     Optional<String> password;

}
