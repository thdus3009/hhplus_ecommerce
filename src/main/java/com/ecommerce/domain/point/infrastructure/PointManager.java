package com.ecommerce.domain.point.infrastructure;

import com.ecommerce.domain.point.entity.UserPoint;
import com.ecommerce.domain.point.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PointManager {
    private final PointReader pointReader;
    private final PointWriter pointWriter;

    public Optional<UserPoint> check(Long userId){
        return pointReader.read(userId);
    }

    public UserPoint save(UserPoint point){
        return pointWriter.save(point);
    }
}

@RequiredArgsConstructor
@Component
class PointReader {
    private final UserPointRepository userPointRepository;
    public Optional<UserPoint> read(Long userId){
        return userPointRepository.findByUserId(userId);
    }
}
@RequiredArgsConstructor
@Component
class PointWriter {
    private final UserPointRepository userPointRepository;
    public UserPoint save(UserPoint userPoint){
        return userPointRepository.save(userPoint);
    }
}
