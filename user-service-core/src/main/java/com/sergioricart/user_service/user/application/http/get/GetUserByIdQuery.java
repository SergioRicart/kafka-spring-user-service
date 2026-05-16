package com.sergioricart.user_service.user.application.http.get;

import com.sergioricart.commons.application.Command;
import com.sergioricart.user_service.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserByIdQuery implements Command<User> {

    private String id;

}
