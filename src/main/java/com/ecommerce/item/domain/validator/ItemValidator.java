package com.ecommerce.item.domain.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.entity.ItemStock;

@Component
public class ItemValidator {
	public void quantityZeroCheck(List<ItemStock> quantityDtos) {
		quantityDtos.stream().filter(dto -> dto.getQuantity() <= 0L)
			.map(ItemStock::getItemId)
			.findFirst()
			.ifPresent(itemId -> {
				throw new CustomException(ErrorCode.ITEM_QUANTITY_ZERO);
			});
	}
}
