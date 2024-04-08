package com.ecommerce.domain.point.service;

import com.ecommerce.api.point.dto.PointResponseDto;
import com.ecommerce.domain.point.entity.PointStatus;
import com.ecommerce.domain.point.entity.UserPoint;
import com.ecommerce.domain.point.entity.UserPointHistory;
import com.ecommerce.domain.point.infrastructure.PointHistoryManager;
import com.ecommerce.domain.point.infrastructure.PointManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PointService {
    private final PointManager pointManager;
    private final PointHistoryManager pointHistoryManager;

    @Transactional
    public PointResponseDto chargePoints(Long userId, Long points) {
        Optional<UserPoint> userPoint = pointManager.check(userId);

        UserPoint newUserPoint;
        if (userPoint.isEmpty()) {
            newUserPoint = new UserPoint(userId, points);
            newUserPoint = pointManager.save(newUserPoint);
        } else {
            // Dirty Checking 해줄 때만 Setter 사용
            newUserPoint = userPoint.orElseThrow(() -> new IllegalStateException("UserPoint 객체가 null입니다."));
            newUserPoint.setPoint(newUserPoint.getPoint() + points);
            //System.out.println(newUserPoint);
            newUserPoint = pointManager.save(newUserPoint);
        }

        UserPointHistory history = new UserPointHistory(userId, PointStatus.CHARGE, points);
        pointHistoryManager.save(history);

        return PointResponseDto.builder()
                .id(newUserPoint.getId())
                .userId(newUserPoint.getUserId())
                .point(newUserPoint.getPoint())
                .createdAt(newUserPoint.getCreatedAt())
                .updatedAt(newUserPoint.getUpdatedAt())
                .build();

//        return new PointResponseDto(
//                newUserPoint.getId(),
//                newUserPoint.getUserId(),
//                newUserPoint.getPoint(),
//                newUserPoint.getCreatedAt(),
//                newUserPoint.getUpdatedAt()
//        );
    }
}
