package com.sergioricart.user_service.user.infrastructure.api.dto.request;

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

     String roleId;

     Instant timestamp;

     String password;

}
