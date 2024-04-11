package com.ecommerce.domain.point.repository.impl;

import com.ecommerce.domain.point.entity.UserPointHistory;
import com.ecommerce.domain.point.repository.UserPointHistoryJpaRepository;
import com.ecommerce.domain.point.repository.UserPointHistoryRepository;
import com.ecommerce.domain.point.repository.UserPointJpaRepository;
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
