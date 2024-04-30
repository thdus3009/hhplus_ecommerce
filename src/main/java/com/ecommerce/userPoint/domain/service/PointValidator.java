package com.ecommerce.userPoint.domain.service;

import org.springframework.stereotype.Component;

import com.ecommerce.userPoint.entity.UserPoint;

@Component
public class PointValidator {
	public void isEnoughPointToPay(UserPoint userPoint, Long points) {
		userPoint.isEnoughPointToPay(points);
	}
}
