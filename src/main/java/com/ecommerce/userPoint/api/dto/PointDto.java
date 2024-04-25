package com.ecommerce.userPoint.api.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class PointDto{
    @Positive
    private Long point;
}

