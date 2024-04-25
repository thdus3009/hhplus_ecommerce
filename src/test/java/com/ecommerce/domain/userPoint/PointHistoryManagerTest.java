package com.ecommerce.domain.userPoint;

import com.ecommerce.userPoint.domain.infrastructure.PointHistoryManagerImpl;
import com.ecommerce.userPoint.domain.repository.UserPointHistoryRepository;
import com.ecommerce.userPoint.entity.PointStatus;
import com.ecommerce.userPoint.entity.UserPointHistory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PointHistoryManagerTest {

    private UserPointHistoryRepository pointHistoryRepository;
    private PointHistoryManagerImpl pointHistoryManager;
    @BeforeEach
    void setUp(){
        pointHistoryRepository = mock(UserPointHistoryRepository.class);
        pointHistoryManager = new PointHistoryManagerImpl(pointHistoryRepository);
    }
    @Test
    void save(){
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
