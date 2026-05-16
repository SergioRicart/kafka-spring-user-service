package com.sergioricart.user_service.user.infrastructure.database;

import com.sergioricart.user_service.user.domain.constant.UserConstants;
import com.sergioricart.user_service.user.domain.entity.User;
import com.sergioricart.user_service.user.domain.exception.UserNotFoundException;
import com.sergioricart.user_service.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityMapper userEntityMapper;
    private final UserRepositoryData userRepositoryData;

    @Override
    public void save(User user) {

        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);

        userRepositoryData.save(userEntity);

    }

    @Override
    public Optional<User> findById(String id) {

        return userRepositoryData.findById(id)
                .map(userEntityMapper::mapToUser);

    }

    @Override
    public Page<User> findAll(int page, int size, String q) {
        String search = StringUtils.hasText(q) ? q : null;
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return userRepositoryData.searchActive(search, pageable)
                .map(userEntityMapper::mapToUser);
    }

    @Override
    public void update(User user) {

        UserEntity userEntity = userRepositoryData.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND_MESSAGE));

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setRoleId(user.getRoleId());
        userEntity.setPassword(user.getPassword());
        userEntity.setUpdatedAt(user.getUpdatedAt());

    }
}
