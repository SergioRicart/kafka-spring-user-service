package com.sergioricart.kafkaSpringUserService.user.infrastructure.database;

import com.sergioricart.kafkaSpringUserService.user.domain.entiry.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

    UserEntity mapToUserEntity(User user);

    User mapToUser(UserEntity userEntity);

}
