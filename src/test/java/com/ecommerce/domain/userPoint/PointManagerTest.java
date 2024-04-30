package com.ecommerce.domain.userPoint;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ecommerce.stub.StubData;
import com.ecommerce.userPoint.domain.infrastructure.PointManagerImpl;
import com.ecommerce.userPoint.domain.repository.UserPointRepository;
import com.ecommerce.userPoint.entity.UserPoint;

public class PointManagerTest {
	private UserPointRepository userPointRepository;
	private PointManagerImpl pointManager;

	@BeforeEach
	void setUp() {
		userPointRepository = mock(UserPointRepository.class);
		pointManager = new PointManagerImpl(userPointRepository);
	}

	@Test
	@DisplayName("포인트 조회")
	void check() {
		// given
		Long userId = 1L;
		UserPoint userPoint = StubData.usePoint(userId);
		given(userPointRepository.findByUserId(anyLong())).willReturn(Optional.of(userPoint));
		// when
		UserPoint res = pointManager.check(userId);
		// then
		Assertions.assertThat(res.getId()).isEqualTo(userPoint.getId());
		Assertions.assertThat(res.getPoint()).isEqualTo(userPoint.getPoint());
	}

	@Test
	@DisplayName("포인트 저장 (충전/사용 시)")
	void savePoint() {
		// given
		Long userId = 1L;
		Long addPoint = 5000L;
		UserPoint userPoint = StubData.usePoint(userId);
		given(userPointRepository.save(any())).willReturn(
			new UserPoint(
				userPoint.getId(),
				userPoint.getUserId(),
				userPoint.getPoint() + addPoint,
				userPoint.getCreatedAt(),
				userPoint.getUpdatedAt()
			)
		);
		// when
		UserPoint res = pointManager.chargePoint(userPoint, addPoint);
		// then
		Assertions.assertThat(res.getId()).isEqualTo(userPoint.getId());
		Assertions.assertThat(res.getPoint()).isEqualTo(userPoint.getPoint());
	}

}
