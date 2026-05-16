package com.sergioricart.user_service.user.infrastructure.database;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryData extends JpaRepository<UserEntity, String> {

    @Query("SELECT u FROM UserEntity u WHERE u.deletedAt IS NULL AND " +
           "(CAST(:q AS string) IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', CAST(:q AS string), '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', CAST(:q AS string), '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', CAST(:q AS string), '%')))")
    Page<UserEntity> searchActive(@Param("q") String q, Pageable pageable);

}
