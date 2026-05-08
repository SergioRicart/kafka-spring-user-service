package com.sergioricart.user_service.user.application.http.update;

import com.sergioricart.user_service.common.application.Command;
import com.sergioricart.user_service.common.application.VoidResponse;
import com.sergioricart.user_service.user.domain.entiry.Role;
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
