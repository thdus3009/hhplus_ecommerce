package com.ecommerce.item.api.dto;

import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemResponseDto {
	private Long id;
	private String name;
	private Long price;
	private Long quantity;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
