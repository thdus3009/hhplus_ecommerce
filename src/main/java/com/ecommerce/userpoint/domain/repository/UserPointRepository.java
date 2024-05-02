package com.ecommerce.userpoint.domain.repository;

import java.util.Optional;

import com.ecommerce.userpoint.entity.UserPoint;

public interface UserPointRepository {
	Optional<UserPoint> findByUserId(Long userId);

	UserPoint save(UserPoint point);
}
