package com.sergioricart.kafkaSpringUserService.user.application.http.delete;

import com.sergioricart.kafkaSpringUserService.common.application.Command;
import com.sergioricart.kafkaSpringUserService.common.application.VoidResponse;
import lombok.Data;

@Data
public class DeleteUserCommand implements Command<VoidResponse> {

    private String id;

}
