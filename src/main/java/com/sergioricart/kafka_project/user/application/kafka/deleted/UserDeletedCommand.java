package com.sergioricart.kafka_project.user.application.kafka.deleted;

import com.sergioricart.kafka_project.common.application.Command;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserDeletedCommand implements Command<VoidResponse> {

    private String id;
    private Instant timestamp;

}
