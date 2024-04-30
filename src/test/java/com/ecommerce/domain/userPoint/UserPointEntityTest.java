package com.ecommerce.domain.userPoint;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ecommerce.stub.StubData;
import com.ecommerce.userPoint.entity.UserPoint;

public class UserPointEntityTest {
	private UserPoint userPoint;

	@BeforeEach
	void Setup() {
		userPoint = StubData.usePoint(1L);
	}

	@Test
	@DisplayName("포인트 충전 테스트")
	void add() {
		// given
		Long addPoint = 5000L;
		Long origin = userPoint.getPoint();
		// when
		userPoint.add(addPoint);
		// then
		Assertions.assertThat(userPoint.getPoint()).isEqualTo(origin + addPoint);
	}

	@Test
	@DisplayName("포인트 차감 테스트")
	void discount() {
		// given
		Long discountPoint = 5000L;
		Long origin = userPoint.getPoint();
		// when
		userPoint.discount(discountPoint);
		// then
		Assertions.assertThat(userPoint.getPoint()).isEqualTo(origin - discountPoint);
	}

	@Test
	@DisplayName("결제시 포인트가 충분한 경우")
	void isEnoughPointToPay() {
		Long point = 5000L;
		org.junit.jupiter.api.Assertions.assertDoesNotThrow(
			() -> userPoint.isEnoughPointToPay(point)
		);
	}

	@Test
	@DisplayName("결제시 포인트가 충분하지 않은 경우")
	void isNotEnoughPointToPay() {
		Long point = 20000L;
		org.junit.jupiter.api.Assertions.assertDoesNotThrow(
			() -> userPoint.isEnoughPointToPay(5000L)
		);
	}

}
