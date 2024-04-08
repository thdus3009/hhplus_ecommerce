package com.ecommerce.api.item.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class ItemResponseDto {
    private Long id;
    private String name;
    private Long price;
    private Long itemCount;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
