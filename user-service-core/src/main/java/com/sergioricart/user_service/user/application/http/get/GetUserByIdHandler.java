package com.sergioricart.user_service.user.application.http.get;

import com.sergioricart.commons.application.CommandHandler;
import com.sergioricart.user_service.user.domain.constant.UserConstants;
import com.sergioricart.user_service.user.domain.entity.User;
import com.sergioricart.user_service.user.domain.exception.UserNotFoundException;
import com.sergioricart.user_service.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetUserByIdHandler implements CommandHandler<GetUserByIdQuery, User> {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User handle(GetUserByIdQuery query) {
        return userRepository.findById(query.getId())
                .orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public Class<GetUserByIdQuery> getCommandType() {
        return GetUserByIdQuery.class;
    }

}
