package com.ecommerce.domain.point.infrastructure;

import com.ecommerce.domain.point.entity.UserPointHistory;
import com.ecommerce.domain.point.repository.UserPointHistoryRepository;
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
