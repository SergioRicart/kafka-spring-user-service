package com.sergioricart.user_service.user.infrastructure.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserResponse {

    String id;

    String firstName;

    String lastName;

    String email;

    String roleId;

    Instant createdAt;

    Instant updatedAt;

    Instant deletedAt;

}
