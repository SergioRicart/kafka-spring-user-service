package com.sergioricart.user_service.user.infrastructure.api.dto.request;

import com.sergioricart.user_service.user.domain.entiry.Role;
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
