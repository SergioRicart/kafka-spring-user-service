package com.sergioricart.kafka_project.user.infrastructure.api.dto.request;

import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatedRequest {

     String firstName;

     String lastName;

     String email;

     Role role;

     Instant timestamp;

     String password;

}
