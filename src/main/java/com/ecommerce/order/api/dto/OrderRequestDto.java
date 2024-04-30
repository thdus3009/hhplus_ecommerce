package com.ecommerce.order.api.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
	@NotNull
	private Long userId;
	private List<OrderItemDto> items;
	@PositiveOrZero
	private Long totalPrice;
	@NotBlank
	private String receiverName;
	@NotBlank
	private String receiverPhone;
	@NotBlank
	private String receiverAddress;

	public Long calculateTotalItemCnt() {
		return items.stream()
			.mapToLong(OrderItemDto::getItemCount)
			.sum();
	}

	public Boolean isItemsNotEmpty() {
		return items != null && !items.isEmpty();
	}
}
