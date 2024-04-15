package com.ecommerce.userPoint.stub;

import com.ecommerce.userPoint.entity.UserPointHistory;
import com.ecommerce.userPoint.domain.infrastructure.PointHistoryManager;

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
