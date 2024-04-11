package com.ecommerce.domain.point.repository.impl;

import com.ecommerce.domain.point.entity.UserPoint;
import com.ecommerce.domain.point.repository.UserPointJpaRepository;
import com.ecommerce.domain.point.repository.UserPointRepository;
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
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public UserPoint save(UserPoint point) {
        return userPointJpaRepository.save(point);
    }
}
