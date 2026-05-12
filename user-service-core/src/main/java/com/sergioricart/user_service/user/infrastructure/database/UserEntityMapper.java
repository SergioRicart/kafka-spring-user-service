package com.sergioricart.user_service.user.infrastructure.database;

import com.sergioricart.user_service.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

    UserEntity mapToUserEntity(User user);

    User mapToUser(UserEntity userEntity);

}
