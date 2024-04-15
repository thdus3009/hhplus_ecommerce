package com.ecommerce.userPoint.domain.infrastructure;

import com.ecommerce.userPoint.entity.UserPoint;

import java.util.Optional;

public interface PointManager {
    Optional<UserPoint> check(Long userId);

    UserPoint save(UserPoint point);
}
