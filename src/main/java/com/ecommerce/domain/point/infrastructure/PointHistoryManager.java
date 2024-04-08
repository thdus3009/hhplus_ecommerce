package com.ecommerce.domain.point.infrastructure;

import com.ecommerce.domain.point.entity.UserPointHistory;
import com.ecommerce.domain.point.repository.UserPointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointHistoryManager {
    private final PointHistoryWriter pointHistoryWriter;

    public void save(UserPointHistory history){
        pointHistoryWriter.save(history);
    }
}

@RequiredArgsConstructor
@Component
class PointHistoryWriter {
    private final UserPointHistoryRepository userPointHistoryRepository;
    public void save(UserPointHistory history){
        userPointHistoryRepository.save(history);
    }
}
