package com.sergioricart.user_service.user.application.http.getAll;

import com.sergioricart.commons.application.Command;
import com.sergioricart.user_service.user.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
public class GetAllUsersQuery implements Command<Page<User>> {

    private int page;
    private int size;
    private String q;

}
