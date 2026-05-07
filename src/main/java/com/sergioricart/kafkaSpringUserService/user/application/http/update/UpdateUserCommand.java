package com.sergioricart.kafkaSpringUserService.user.application.http.update;

import com.sergioricart.kafkaSpringUserService.common.application.Command;
import com.sergioricart.kafkaSpringUserService.common.application.VoidResponse;
import com.sergioricart.kafkaSpringUserService.user.domain.entiry.Role;
import lombok.Data;

@Data
public class UpdateUserCommand implements Command<VoidResponse> {

    private String id;
    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private String password;


}
