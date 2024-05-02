package com.ecommerce.userpoint.domain.service;

import org.springframework.stereotype.Component;

import com.ecommerce.userpoint.entity.UserPoint;

@Component
public class PointValidator {
	public void isEnoughPointToPay(UserPoint userPoint, Long points) {
		userPoint.isEnoughPointToPay(points);
	}
}
