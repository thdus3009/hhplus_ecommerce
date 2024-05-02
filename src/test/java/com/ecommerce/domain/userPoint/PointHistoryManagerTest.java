package com.ecommerce.domain.userPoint;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecommerce.userpoint.domain.infrastructure.PointHistoryManagerImpl;
import com.ecommerce.userpoint.domain.repository.UserPointHistoryRepository;
import com.ecommerce.userpoint.entity.PointStatus;
import com.ecommerce.userpoint.entity.UserPointHistory;

public class PointHistoryManagerTest {

	private UserPointHistoryRepository pointHistoryRepository;
	private PointHistoryManagerImpl pointHistoryManager;

	@BeforeEach
	void setUp() {
		pointHistoryRepository = mock(UserPointHistoryRepository.class);
		pointHistoryManager = new PointHistoryManagerImpl(pointHistoryRepository);
	}

	@Test
	void save() {
		// given
		Long userId = 1L;
		UserPointHistory history = new UserPointHistory(userId, PointStatus.CHARGE, 5000L);
		given(pointHistoryRepository.save(history)).willReturn(history);
		// when
		UserPointHistory res = pointHistoryManager.save(history);
		// then
		Assertions.assertThat(res.getType()).isEqualTo(history.getType());
		Assertions.assertThat(res.getPoint()).isEqualTo(history.getPoint());
	}
}
