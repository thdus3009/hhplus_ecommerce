package com.ecommerce.order.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDto {
	private String uuid; // 주문에 대한 UUID
	private Long userId; // 유저 아이디
	private Long totalPrice;
	private Long totalCount;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddress;

	public OrderDto(String uuid, Long userId, Long totalPrice, Long totalCount, String receiverName,
		String receiverPhone, String receiverAddress) {
		this.uuid = uuid;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.totalCount = totalCount;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
	}
}