package com.sergioricart.user_service.user.application.http.delete;

import com.sergioricart.user_service.common.application.Command;
import com.sergioricart.user_service.common.application.VoidResponse;
import lombok.Data;

@Data
public class DeleteUserCommand implements Command<VoidResponse> {

    private String id;

}
