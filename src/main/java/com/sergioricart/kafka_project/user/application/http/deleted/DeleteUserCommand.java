package com.sergioricart.kafka_project.user.application.http.deleted;

import com.sergioricart.kafka_project.common.application.Command;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import lombok.Data;

import java.time.Instant;

@Data
public class DeleteUserCommand implements Command<VoidResponse> {

    private String id;

}
