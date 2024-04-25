package com.ecommerce.userPoint.domain.service;

import com.ecommerce.userPoint.entity.UserPoint;
import org.springframework.stereotype.Component;

@Component
public class PointValidator {
    public void isEnoughPointToPay(UserPoint userPoint, Long points){
        userPoint.isEnoughPointToPay(points);
    }
}
