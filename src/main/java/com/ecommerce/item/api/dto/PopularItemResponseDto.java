package com.ecommerce.item.api.dto;

import lombok.Getter;

@Getter
public class PopularItemResponseDto {
	private Long itemId;
	private String name;
	private Long price;
	private Long totalPurchase;

	public PopularItemResponseDto() {
	}

	public PopularItemResponseDto(Long itemId, String name, Long price, Long totalPurchase) {
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.totalPurchase = totalPurchase;
	}
}
