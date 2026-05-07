package com.sergioricart.kafka_project.user.application.http.delete;

import com.sergioricart.kafka_project.common.application.Command;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import lombok.Data;

@Data
public class DeleteUserCommand implements Command<VoidResponse> {

    private String id;

}
