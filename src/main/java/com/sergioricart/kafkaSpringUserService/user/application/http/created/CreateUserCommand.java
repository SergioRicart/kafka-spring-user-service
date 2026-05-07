package com.sergioricart.kafkaSpringUserService.user.application.http.created;

import com.sergioricart.kafkaSpringUserService.common.application.Command;
import com.sergioricart.kafkaSpringUserService.common.application.VoidResponse;
import lombok.Data;

@Data
public class CreateUserCommand implements Command<VoidResponse> {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
