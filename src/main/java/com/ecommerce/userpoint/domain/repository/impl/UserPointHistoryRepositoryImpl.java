package com.ecommerce.userpoint.domain.repository.impl;

import org.springframework.stereotype.Component;

import com.ecommerce.userpoint.domain.repository.UserPointHistoryJpaRepository;
import com.ecommerce.userpoint.domain.repository.UserPointHistoryRepository;
import com.ecommerce.userpoint.entity.UserPointHistory;

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
