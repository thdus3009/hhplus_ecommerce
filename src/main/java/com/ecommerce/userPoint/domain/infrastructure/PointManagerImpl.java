package com.ecommerce.userPoint.domain.infrastructure;

import com.ecommerce.userPoint.entity.UserPoint;
import com.ecommerce.userPoint.domain.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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