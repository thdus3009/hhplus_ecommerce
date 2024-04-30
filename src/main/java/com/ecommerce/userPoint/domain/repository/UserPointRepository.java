package com.ecommerce.userPoint.domain.repository;

import java.util.Optional;

import com.ecommerce.userPoint.entity.UserPoint;

public interface UserPointRepository {
	Optional<UserPoint> findByUserId(Long userId);

	UserPoint save(UserPoint point);
}
