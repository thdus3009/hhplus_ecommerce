package com.ecommerce.userPoint.domain.repository.impl;

import org.springframework.stereotype.Component;

import com.ecommerce.userPoint.domain.repository.UserPointHistoryJpaRepository;
import com.ecommerce.userPoint.domain.repository.UserPointHistoryRepository;
import com.ecommerce.userPoint.entity.UserPointHistory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserPointHistoryRepositoryImpl implements UserPointHistoryRepository {
	private final UserPointHistoryJpaRepository userPointHistoryJpaRepository;

	@Override
	public UserPointHistory save(UserPointHistory history) {
		return userPointHistoryJpaRepository.save(history);
	}
}
