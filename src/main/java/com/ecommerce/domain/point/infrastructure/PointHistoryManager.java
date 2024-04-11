package com.ecommerce.domain.point.infrastructure;

import com.ecommerce.domain.point.entity.UserPointHistory;
import com.ecommerce.domain.point.repository.UserPointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public interface PointHistoryManager {
    UserPointHistory save(UserPointHistory history);
}

