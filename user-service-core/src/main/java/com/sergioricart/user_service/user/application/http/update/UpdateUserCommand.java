package com.sergioricart.user_service.user.application.http.update;

import com.sergioricart.commons.application.Command;
import com.sergioricart.commons.application.VoidResponse;
import lombok.Data;

@Data
public class UpdateUserCommand implements Command<VoidResponse> {

    private String id;
    private String firstName;
    private String lastName;
    private String roleId;
    private String email;
    private String password;


}
