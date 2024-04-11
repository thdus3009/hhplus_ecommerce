package com.ecommerce.domain.point.infrastructure;

import com.ecommerce.domain.point.entity.UserPoint;

import java.util.Optional;

public interface PointManager {
    Optional<UserPoint> check(Long userId);

    UserPoint save(UserPoint point);
}
