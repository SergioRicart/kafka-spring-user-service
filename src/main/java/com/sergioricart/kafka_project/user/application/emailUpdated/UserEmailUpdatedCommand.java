package com.sergioricart.kafka_project.user.application.emailUpdated;

import com.sergioricart.kafka_project.common.application.Command;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserEmailUpdatedCommand implements Command<VoidResponse> {

    private Long id;
    private String email;
    private Instant timestamp;

}
