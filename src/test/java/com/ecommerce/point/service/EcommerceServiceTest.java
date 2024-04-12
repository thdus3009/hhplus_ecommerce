package com.ecommerce.point.service;

import com.ecommerce.api.point.dto.PointResponseDto;
import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.domain.point.service.PointService;
import com.ecommerce.point.stub.UserPointHistoryManagerStub;
import com.ecommerce.point.stub.UserPointManagerStub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EcommerceServiceTest.class)
public class EcommerceServiceTest {
    PointService pointService;
    UserPointManagerStub pointManager;
    UserPointHistoryManagerStub pointHistoryManager;

    public EcommerceServiceTest(){
        pointManager = new UserPointManagerStub();
        pointHistoryManager = new UserPointHistoryManagerStub();
        pointService = new PointService(pointManager, pointHistoryManager);
    }
    @BeforeEach
    public void beforeEach(){
        pointManager.clear();
        pointHistoryManager.clear();
    }

    @Test
    public void 포인트_충전(){
        // given
        Long userId = 1L;
        Long points = 20000L;
        // when
        PointResponseDto res = pointService.chargePoint(userId, points);
        // then
        Assertions.assertThat(res.getPoint()).isEqualTo(15000L+points);
    }
    @Test
    public void 포인트_조회(){
        // given
        Long userId = 1L;
        // when
        PointResponseDto res = pointService.getPoint(userId);
        // then
        Assertions.assertThat(res.getPoint()).isEqualTo(15000L);
    }

    @Test
    public void 포인트_조회_실패(){
        // given
        Long userId = 2L;
        // when then
        Assertions.assertThatThrownBy(()-> pointService.getPoint(userId))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.USER_POINT_NULL);
    }
}
