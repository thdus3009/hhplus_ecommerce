package com.ecommerce.userPoint.domain.service;

import com.ecommerce.userPoint.api.dto.PointResponseDto;
import com.ecommerce.userPoint.entity.PointStatus;
import com.ecommerce.userPoint.entity.UserPoint;
import com.ecommerce.userPoint.entity.UserPointHistory;
import com.ecommerce.userPoint.domain.repository.UserPointHistoryRepository;
import com.ecommerce.userPoint.domain.repository.UserPointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PointService {
    private final UserPointRepository pointRepository;
    private final UserPointHistoryRepository pointHistoryRepository;

    /**
     *
     * @param userId 유저 아이디
     * @param points 충전할 포인트
     * @return 저장된 UserPoint 정보
     */
    @Transactional
    public PointResponseDto chargePoint(Long userId, Long points) {
        UserPoint userPoint = pointRepository.findByUserId(userId)
                .orElse(new UserPoint(userId,0L));

        userPoint.add(points);
        userPoint = pointRepository.save(userPoint);

        historySave(userId, PointStatus.CHARGE, points);

        return toDto(userPoint);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserPoint usePoint(Long userId, Long points){
        UserPoint userPoint = pointRepository.findByUserId(userId)
                .orElse(new UserPoint(userId,0L));

        userPoint.discount(points);
        userPoint = pointRepository.save(userPoint);

        historySave(userId, PointStatus.USE, points);

        return userPoint;
    }

    public void historySave(Long userId, PointStatus status, Long points){
        UserPointHistory history = new UserPointHistory(userId, status, points);
        pointHistoryRepository.save(history);
    }

    /**
     *
     * @param userId 유저 아이디
     * @return
     */
    public PointResponseDto getPoint(Long userId) {
        // validator에 넣기
        UserPoint userPoint = pointRepository.findByUserId(userId)
                .orElse(new UserPoint(userId,0L));
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
