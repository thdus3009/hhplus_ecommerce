package com.ecommerce.userPoint.stub;

import com.ecommerce.userPoint.entity.UserPoint;
import com.ecommerce.userPoint.domain.infrastructure.PointManager;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserPointManagerStub implements PointManager {
    Map<Long, UserPoint> store = new ConcurrentHashMap<>();
    // 멀티스레드 환경에서 안전하게 고유 id를 생성하기 위해 atomic 타입 사용
    AtomicLong idSeq = new AtomicLong(1L);
    public void clear(){
        store.clear();
        idSeq.set(1L);

        store.put(
                idSeq.getAndAdd(1L),
                new UserPoint(
                        1L,
                        1L,
                        15000L,
                        ZonedDateTime.now(),
                        null
                )
        );
    }
    public Optional<UserPoint> check(Long userId) {
        return Optional.ofNullable(store.get(userId));
    }

    public UserPoint save(UserPoint userPoint) {
        UserPoint userPoint1 = new UserPoint(
                userPoint.getId(),
                userPoint.getUserId(),
                userPoint.getPoint(),
                userPoint.getCreatedAt(),
                userPoint.getUpdatedAt()
        );
        return store.put(userPoint.getId(), userPoint1);
    }
}
