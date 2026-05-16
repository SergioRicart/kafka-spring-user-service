package com.sergioricart.user_service.user.application.http.getAll;

import com.sergioricart.commons.application.CommandHandler;
import com.sergioricart.user_service.user.domain.entity.User;
import com.sergioricart.user_service.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetAllUsersHandler implements CommandHandler<GetAllUsersQuery, Page<User>> {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll(query.getPage(), query.getSize(), query.getQ());
    }

    @Override
    public Class<GetAllUsersQuery> getCommandType() {
        return GetAllUsersQuery.class;
    }

}
