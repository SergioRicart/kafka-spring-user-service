package com.sergioricart.kafka_project.user.infrastructure.api.mapper;

import com.sergioricart.kafka_project.user.application.http.created.CreateUserCommand;
import com.sergioricart.kafka_project.user.application.http.update.UpdateUserCommand;
import com.sergioricart.kafka_project.user.infrastructure.api.dto.request.UserCreatedRequest;
import com.sergioricart.kafka_project.user.infrastructure.api.dto.request.UserUpdatedRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserApiMapper {

    CreateUserCommand mapToCreateUserCommand(UserCreatedRequest request);

    UpdateUserCommand mapToUpdateUserCommand(UserUpdatedRequest request);

}
