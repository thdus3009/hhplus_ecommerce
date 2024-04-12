package com.ecommerce.domain.point.service;

import com.ecommerce.api.point.dto.PointResponseDto;
import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.domain.point.entity.PointStatus;
import com.ecommerce.domain.point.entity.UserPoint;
import com.ecommerce.domain.point.entity.UserPointHistory;
import com.ecommerce.domain.point.infrastructure.PointHistoryManager;
import com.ecommerce.domain.point.infrastructure.PointManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PointService {
    private final PointManager pointManager;
    private final PointHistoryManager pointHistoryManager;

    /**
     *
     * @param userId 유저 아이디
     * @param points 충전할 포인트
     * @return 저장된 UserPoint 정보
     */
    @Transactional
    public PointResponseDto chargePoint(Long userId, Long points) {
        Optional<UserPoint> userPoint = pointManager.check(userId);

        UserPoint newUserPoint;
        if (userPoint.isEmpty()) {
            newUserPoint = new UserPoint(userId, points);
            newUserPoint = pointManager.save(newUserPoint);
        } else {
            // Optional > UserPoint로 변경
            newUserPoint = userPoint.orElseThrow(() -> new CustomException(ErrorCode.USER_POINT_NULL));
            // Dirty Checking 해줄 때만 Setter 사용
            newUserPoint.setPoint(newUserPoint.getPoint() + points);
            newUserPoint = pointManager.save(newUserPoint);
        }

        UserPointHistory history = new UserPointHistory(userId, PointStatus.CHARGE, points);
        pointHistoryManager.save(history);

        return toDto(newUserPoint);
    }

    /**
     *
     * @param userId 유저 아이디
     * @return
     */
    public PointResponseDto getPoint(Long userId) {
        // validator에 넣기
        UserPoint userPoint = pointManager.check(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_POINT_NULL));
        return toDto(userPoint);
    }

    public PointResponseDto toDto(UserPoint newUserPoint){
        return PointResponseDto.builder()
                .id(newUserPoint.getId())
                .userId(newUserPoint.getUserId())
                .point(newUserPoint.getPoint())
                .createdAt(newUserPoint.getCreatedAt())
                .updatedAt(newUserPoint.getUpdatedAt())
                .build();
    }
}
