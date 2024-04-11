package com.ecommerce.stub.point;

import com.ecommerce.domain.point.entity.UserPointHistory;
import com.ecommerce.domain.point.infrastructure.PointHistoryManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserPointHistoryManagerStub implements PointHistoryManager {
    Map<Long, UserPointHistory> store = new ConcurrentHashMap<>();
    AtomicLong idSeq = new AtomicLong(1L);

    public void clear(){
        store.clear();
    }

    @Override
    public UserPointHistory save(UserPointHistory history) {
        return store.put(
                idSeq.getAndAdd(1L),
                history
        );
    }
}
