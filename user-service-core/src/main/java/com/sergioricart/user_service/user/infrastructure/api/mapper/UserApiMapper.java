package com.sergioricart.user_service.user.infrastructure.api.mapper;

import com.sergioricart.user_service.user.application.http.created.CreateUserCommand;
import com.sergioricart.user_service.user.application.http.update.UpdateUserCommand;
import com.sergioricart.user_service.user.domain.entity.User;
import com.sergioricart.user_service.user.infrastructure.api.dto.request.UserCreatedRequest;
import com.sergioricart.user_service.user.infrastructure.api.dto.request.UserUpdatedRequest;
import com.sergioricart.user_service.user.infrastructure.api.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserApiMapper {

    CreateUserCommand mapToCreateUserCommand(UserCreatedRequest request);

    UpdateUserCommand mapToUpdateUserCommand(UserUpdatedRequest request);

    UserResponse mapToUserResponse(User user);

    List<UserResponse> mapToUserResponseList(List<User> users);

}
