package com.sergioricart.kafka_project.user.application;

import com.sergioricart.kafka_project.common.application.Command;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserCreatedCommand implements Command<VoidResponse> {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private Instant timestamp;

}
