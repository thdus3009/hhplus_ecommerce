package com.ecommerce.userpoint.domain.infrastructure;

import org.springframework.stereotype.Component;

import com.ecommerce.userpoint.domain.repository.UserPointHistoryRepository;
import com.ecommerce.userpoint.entity.UserPointHistory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PointHistoryManagerImpl implements PointHistoryManager {
	private final UserPointHistoryRepository userPointHistoryRepository;

	@Override
	public UserPointHistory save(UserPointHistory history) {
		return userPointHistoryRepository.save(history);
	}
}
