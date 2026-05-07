package com.sergioricart.kafka_project.user.application.kafka.updated;

import com.sergioricart.kafka_project.common.application.Command;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserUpdatedCommand implements Command<VoidResponse> {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Instant timestamp;

}
