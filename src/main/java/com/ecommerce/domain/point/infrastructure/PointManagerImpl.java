package com.ecommerce.domain.point.infrastructure;

import com.ecommerce.domain.point.entity.UserPoint;
import com.ecommerce.domain.point.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PointManagerImpl implements PointManager {
    private final UserPointRepository userPointRepository;

    @Override
    public Optional<UserPoint> check(Long userId) {
        return userPointRepository.findByUserId(userId);
    }

    @Override
    public UserPoint save(UserPoint point) {
        return userPointRepository.save(point);
    }
}