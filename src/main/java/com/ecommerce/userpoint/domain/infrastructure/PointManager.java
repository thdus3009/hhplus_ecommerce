package com.ecommerce.userpoint.domain.infrastructure;

import com.ecommerce.userpoint.entity.UserPoint;

public interface PointManager {
	UserPoint check(Long userId);

	UserPoint usePoint(UserPoint userPoint, Long points);

	UserPoint chargePoint(UserPoint userPoint, Long points);
}
