package com.ecommerce.userpoint.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.aop.redissonLock.RedissonLock;
import com.ecommerce.userpoint.api.dto.PointResponseDto;
import com.ecommerce.userpoint.domain.infrastructure.PointHistoryManager;
import com.ecommerce.userpoint.domain.infrastructure.PointManager;
import com.ecommerce.userpoint.entity.PointStatus;
import com.ecommerce.userpoint.entity.UserPoint;
import com.ecommerce.userpoint.entity.UserPointHistory;

@Service
public class PointService {
	private final PointManager pointManager;
	private final PointHistoryManager pointHistoryManager;
	private final PointValidator pointValidator;

	public PointService(PointManager pointManager, PointHistoryManager pointHistoryManager,
		PointValidator pointValidator) {
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
	@RedissonLock(key = "#userId", identifier = "userId")
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
	public UserPoint usePoint(Long userId, Long points) {
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
	public void historySave(Long userId, PointStatus status, Long points) {
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

	public PointResponseDto toDto(UserPoint userPoint) {
		return PointResponseDto.builder()
			.id(userPoint.getId())
			.userId(userPoint.getUserId())
			.point(userPoint.getPoint())
			.createdAt(userPoint.getCreatedAt())
			.updatedAt(userPoint.getUpdatedAt())
			.build();
	}
}
