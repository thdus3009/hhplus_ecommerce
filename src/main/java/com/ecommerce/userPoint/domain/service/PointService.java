package com.ecommerce.userPoint.domain.service;

import com.ecommerce.userPoint.api.dto.PointResponseDto;
import com.ecommerce.userPoint.domain.infrastructure.PointHistoryManager;
import com.ecommerce.userPoint.domain.infrastructure.PointHistoryManagerImpl;
import com.ecommerce.userPoint.domain.infrastructure.PointManager;
import com.ecommerce.userPoint.domain.infrastructure.PointManagerImpl;
import com.ecommerce.userPoint.entity.PointStatus;
import com.ecommerce.userPoint.entity.UserPoint;
import com.ecommerce.userPoint.entity.UserPointHistory;
import com.ecommerce.userPoint.domain.repository.UserPointHistoryRepository;
import com.ecommerce.userPoint.domain.repository.UserPointRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointService {
    private final PointManager pointManager;
    private final PointHistoryManager pointHistoryManager;
    private final PointValidator pointValidator;

    public PointService(PointManager pointManager, PointHistoryManager pointHistoryManager, PointValidator pointValidator){
        this.pointManager = pointManager;
        this.pointHistoryManager = pointHistoryManager;
        this.pointValidator = pointValidator;
    }
    /**
     * - 포인트 충전
     * @param userId 유저 아이디
     * @param points 충전할 포인트
     * @return 저장된 UserPoint 정보
     */
    @Transactional
    public PointResponseDto chargePoint(Long userId, Long points) {
        UserPoint userPoint = pointManager.check(userId);
        userPoint = pointManager.chargePoint(userPoint, points);

        historySave(userId, PointStatus.CHARGE, points);

        return toDto(userPoint);
    }

    /**
     * - 포인트 사용
     * @param userId 유저 아이디
     * @param points 사용할 포인트
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public UserPoint usePoint(Long userId, Long points){
        UserPoint userPoint = pointManager.check(userId);

        pointValidator.isEnoughPointToPay(userPoint, points);
        userPoint = pointManager.usePoint(userPoint, points);

        historySave(userId, PointStatus.USE, points);

        return userPoint;
    }

    /**
     * - 포인트 히스토리 저장
     * @param userId 유저 아이디
     * @param status 포인트 상태 (CHARGE, USE)
     * @param points 포인트
     */
    public void historySave(Long userId, PointStatus status, Long points){
        UserPointHistory history = new UserPointHistory(userId, status, points);
        pointHistoryManager.save(history);
    }

    /**
     * - 포인트 조회
     * @param userId 유저 아이디
     * @return
     */
    public PointResponseDto getPoint(Long userId) {
        // validator에 넣기
        UserPoint userPoint = pointManager.check(userId);
        return toDto(userPoint);
    }

    public PointResponseDto toDto(UserPoint userPoint){
        return PointResponseDto.builder()
                .id(userPoint.getId())
                .userId(userPoint.getUserId())
                .point(userPoint.getPoint())
                .createdAt(userPoint.getCreatedAt())
                .updatedAt(userPoint.getUpdatedAt())
                .build();
    }
}