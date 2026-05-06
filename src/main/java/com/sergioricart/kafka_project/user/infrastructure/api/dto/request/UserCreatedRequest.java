package com.sergioricart.kafka_project.user.infrastructure.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    String firstName;

    String lastName;

    @NotBlank(message = "El email no puede ser nulo")
    String email;

    @NotBlank(message = "La contraseña no puede ser nula")
    String password;

}
