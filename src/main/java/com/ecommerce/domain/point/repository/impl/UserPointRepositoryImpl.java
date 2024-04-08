package com.ecommerce.domain.point.repository.impl;

import com.ecommerce.domain.point.entity.UserPoint;
import com.ecommerce.domain.point.repository.UserPointJpaRepository;
import com.ecommerce.domain.point.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;
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
