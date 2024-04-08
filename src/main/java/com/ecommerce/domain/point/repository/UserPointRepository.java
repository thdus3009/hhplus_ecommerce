package com.ecommerce.domain.point.repository;

import com.ecommerce.domain.point.entity.UserPoint;

import java.util.Optional;

public interface UserPointRepository {
    Optional<UserPoint> findByUserId(Long userId);
    UserPoint save(UserPoint point);
}
