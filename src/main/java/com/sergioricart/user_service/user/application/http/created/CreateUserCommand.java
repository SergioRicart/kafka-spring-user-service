package com.sergioricart.user_service.user.application.http.created;

import com.sergioricart.commons.application.Command;
import com.sergioricart.commons.application.VoidResponse;
import lombok.Data;

@Data
public class CreateUserCommand implements Command<VoidResponse> {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
