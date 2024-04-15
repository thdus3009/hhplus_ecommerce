package com.ecommerce.userPoint.domain.repository.impl;

import com.ecommerce.userPoint.entity.UserPointHistory;
import com.ecommerce.userPoint.domain.repository.UserPointHistoryJpaRepository;
import com.ecommerce.userPoint.domain.repository.UserPointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPointHistoryRepositoryImpl implements UserPointHistoryRepository {
    private final UserPointHistoryJpaRepository userPointHistoryJpaRepository;
    @Override
    public UserPointHistory save(UserPointHistory history) {
        return userPointHistoryJpaRepository.save(history);
    }
}
