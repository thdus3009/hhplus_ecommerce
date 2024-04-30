package com.ecommerce.userPoint.domain.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ecommerce.userPoint.domain.repository.UserPointJpaRepository;
import com.ecommerce.userPoint.domain.repository.UserPointRepository;
import com.ecommerce.userPoint.entity.UserPoint;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserPointRepositoryImpl implements UserPointRepository {
	private final UserPointJpaRepository userPointJpaRepository;

	@Override
	public Optional<UserPoint> findByUserId(Long userId) {
		return userPointJpaRepository.findByUserId(userId);
	}

	@Override
	public UserPoint save(UserPoint point) {
		return userPointJpaRepository.save(point);
	}
}
