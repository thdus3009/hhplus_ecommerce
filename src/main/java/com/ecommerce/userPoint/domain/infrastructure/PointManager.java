package com.ecommerce.userPoint.domain.infrastructure;

import com.ecommerce.userPoint.domain.repository.UserPointRepository;
import com.ecommerce.userPoint.entity.UserPoint;

import java.util.Optional;

public interface PointManager {
    UserPoint check(Long userId);
    UserPoint usePoint(UserPoint userPoint, Long points);
    UserPoint chargePoint(UserPoint userPoint, Long points);
}
