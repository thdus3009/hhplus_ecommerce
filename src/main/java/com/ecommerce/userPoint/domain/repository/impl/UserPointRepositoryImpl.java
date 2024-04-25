package com.ecommerce.userPoint.domain.repository.impl;

import com.ecommerce.userPoint.domain.repository.UserPointJpaRepository;
import com.ecommerce.userPoint.domain.repository.UserPointRepository;
import com.ecommerce.userPoint.entity.UserPoint;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserPointRepositoryImpl implements UserPointRepository {
    private final UserPointJpaRepository userPointJpaRepository;

    @Override
    public Optional<UserPoint> findByUserId(Long userId) {
        return userPointJpaRepository.findByUserId(userId);
    }

    @Override
    public UserPoint save(UserPoint point) {
        return userPointJpaRepository.save(point);
    }
}
