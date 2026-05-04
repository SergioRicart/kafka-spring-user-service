package com.sergioricart.kafka_project.user.infrastructure.database;

import com.sergioricart.kafka_project.user.domain.entiry.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

    UserEntity mapToUserEntity(User user);

    User mapToUser(UserEntity userEntity);
}
