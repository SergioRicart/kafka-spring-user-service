package com.sergioricart.user_service.user.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryData extends JpaRepository<UserEntity, String> {

}
