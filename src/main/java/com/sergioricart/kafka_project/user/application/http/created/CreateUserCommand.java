package com.sergioricart.kafka_project.user.application.http.created;

import com.sergioricart.kafka_project.common.application.Command;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class CreateUserCommand implements Command<VoidResponse> {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
