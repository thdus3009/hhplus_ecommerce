package com.ecommerce.domain.userPoint;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ecommerce.stub.StubData;
import com.ecommerce.userpoint.api.dto.PointResponseDto;
import com.ecommerce.userpoint.domain.infrastructure.PointHistoryManager;
import com.ecommerce.userpoint.domain.infrastructure.PointManager;
import com.ecommerce.userpoint.domain.service.PointService;
import com.ecommerce.userpoint.domain.service.PointValidator;
import com.ecommerce.userpoint.entity.UserPoint;

@WebMvcTest(PointService.class)
public class PointServiceTest {

	@MockBean
	private PointManager pointManager;
	@MockBean
	private PointHistoryManager pointHistoryManager;
	@MockBean
	private PointValidator pointValidator;
	private PointService pointService;

	@BeforeEach
	void setUp() {
		pointService = new PointService(pointManager, pointHistoryManager, pointValidator);
	}

	@Test
	@DisplayName("포인트 조회")
	void getPoint() {
		// given
		Long userId = 1L;
		UserPoint userPoint = StubData.usePoint(userId);
		given(pointManager.check(anyLong())).willReturn(userPoint);
		// when
		PointResponseDto pointResponseDto = pointService.getPoint(userId);
		// then
		Assertions.assertThat(pointResponseDto.getId()).isEqualTo(userPoint.getId());
		Assertions.assertThat(pointResponseDto.getPoint()).isEqualTo(userPoint.getPoint());
	}

	@Test
	@DisplayName("포인트 사용")
	void usePoint() {
		// given
		Long userId = 1L;
		Long points = 5000L;
		UserPoint userPoint = StubData.usePoint(userId);

		given(pointManager.check(anyLong())).willReturn(userPoint);
		given(pointManager.usePoint(any(), anyLong())).willReturn(
			new UserPoint(
				userPoint.getId(),
				userPoint.getUserId(),
				userPoint.getPoint() - points,
				userPoint.getCreatedAt(),
				userPoint.getUpdatedAt()
			)
		);
		// when
		UserPoint resultPoint = pointService.usePoint(userId, points);
		// then
		Assertions.assertThat(resultPoint.getId()).isEqualTo(userPoint.getId());
		Assertions.assertThat(resultPoint.getPoint()).isEqualTo(userPoint.getPoint() - points);
	}

	@Test
	@DisplayName("포인트 충전")
	void chargePoint() {
		// given
		Long userId = 1L;
		Long points = 5000L;
		UserPoint userPoint = StubData.usePoint(userId);

		given(pointManager.check(anyLong())).willReturn(userPoint);
		given(pointManager.chargePoint(any(), anyLong())).willReturn(
			new UserPoint(
				userPoint.getId(),
				userPoint.getUserId(),
				userPoint.getPoint() + points,
				userPoint.getCreatedAt(),
				userPoint.getUpdatedAt()
			)
		);
		// when
		PointResponseDto responseDto = pointService.chargePoint(userId, points);
		// then
		Assertions.assertThat(responseDto.getId()).isEqualTo(userPoint.getId());
		Assertions.assertThat(responseDto.getPoint()).isEqualTo(userPoint.getPoint() + points);
	}

}
