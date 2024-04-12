package com.ecommerce.api.point.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class PointResponseDto{
    private Long id;
    private Long userId;
    private Long point;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

//    public PointResponseDto(UserPoint userPoint) {
//        this.id = userPoint.getId();
//        this.userId = userPoint.getUserId();
//        this.point = userPoint.getPoint();
//        this.createdAt = userPoint.getCreatedAt();
//        this.updatedAt = userPoint.getUpdatedAt();
//    }

//    public PointResponseDto(Long id, Long userId, Long point, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
//        this.id = id;
//        this.userId = userId;
//        this.point = point;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
}