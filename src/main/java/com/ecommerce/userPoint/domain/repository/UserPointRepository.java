package com.ecommerce.userPoint.domain.repository;

import com.ecommerce.userPoint.entity.UserPoint;

import java.util.Optional;

public interface UserPointRepository {
    Optional<UserPoint> findByUserId(Long userId);
    UserPoint save(UserPoint point);
}
