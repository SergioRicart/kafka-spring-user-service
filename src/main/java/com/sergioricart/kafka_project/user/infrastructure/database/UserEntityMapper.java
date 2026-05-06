package com.sergioricart.kafka_project.user.infrastructure.database;

import com.sergioricart.kafka_project.user.domain.entiry.User;
import com.sergioricart.kafka_project.user.infrastructure.api.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

    UserEntity mapToUserEntity(User user);

    UserEntity mapToUserEntity(UserResponse user);

    User mapToUser(UserEntity userEntity);

    UserResponse mapToUserResponse(User user);

    UserResponse mapToUserResponse(UserEntity userEntity);


}
