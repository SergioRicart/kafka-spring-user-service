package com.sergioricart.user_service.user.application.http.delete;

import com.sergioricart.commons.application.Command;
import com.sergioricart.commons.application.VoidResponse;
import lombok.Data;

@Data
public class DeleteUserCommand implements Command<VoidResponse> {

    private String id;

}
