package com.sergioricart.user_service.user.infrastructure.api.dto.response;

import com.sergioricart.user_service.user.domain.entity.Role;
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

    Role role;

    Instant createdAt;

    Instant updatedAt;

    Instant deletedAt;

}
