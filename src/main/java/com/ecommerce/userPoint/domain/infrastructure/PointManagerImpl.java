package com.ecommerce.userPoint.domain.infrastructure;

import org.springframework.stereotype.Component;

import com.ecommerce.userPoint.domain.repository.UserPointRepository;
import com.ecommerce.userPoint.entity.UserPoint;

@Component
public class PointManagerImpl implements PointManager {
	private final UserPointRepository userPointRepository;

	public PointManagerImpl(UserPointRepository userPointRepository) {
		this.userPointRepository = userPointRepository;
	}

	@Override
	public UserPoint check(Long userId) {
		return userPointRepository.findByUserId(userId)
			.orElse(new UserPoint(userId, 0L));
	}

	@Override
	public UserPoint usePoint(UserPoint userPoint, Long points) {
		userPoint.discount(points);
		return userPointRepository.save(userPoint);
	}

	@Override
	public UserPoint chargePoint(UserPoint userPoint, Long points) {
		userPoint.add(points);
		return userPointRepository.save(userPoint);
	}
}