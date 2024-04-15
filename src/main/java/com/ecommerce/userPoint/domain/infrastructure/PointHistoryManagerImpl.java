package com.ecommerce.userPoint.domain.infrastructure;

import com.ecommerce.userPoint.entity.UserPointHistory;
import com.ecommerce.userPoint.domain.repository.UserPointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointHistoryManagerImpl implements PointHistoryManager{
    private final UserPointHistoryRepository userPointHistoryRepository;

    @Override
    public UserPointHistory save(UserPointHistory history){
        return userPointHistoryRepository.save(history);
    }
}
