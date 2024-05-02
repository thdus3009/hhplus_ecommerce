package com.ecommerce.userpoint.domain.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ecommerce.userpoint.domain.repository.UserPointJpaRepository;
import com.ecommerce.userpoint.domain.repository.UserPointRepository;
import com.ecommerce.userpoint.entity.UserPoint;

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
