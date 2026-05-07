package com.sergioricart.kafka_project.user.application.http.update;

import com.sergioricart.kafka_project.common.application.Command;
import com.sergioricart.kafka_project.common.application.VoidResponse;
import com.sergioricart.kafka_project.user.domain.entiry.Role;
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
