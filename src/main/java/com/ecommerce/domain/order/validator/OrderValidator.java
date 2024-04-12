package com.ecommerce.domain.order.validator;

import com.ecommerce.api.order.dto.OrderItemDto;
import com.ecommerce.api.order.dto.OrderRequestDto;
import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.domain.item.entity.Item;
import com.ecommerce.domain.item.entity.ItemStock;
import com.ecommerce.domain.point.entity.UserPoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderValidator {
    public void quantityZeroCheck(List<ItemStock> quantityDtos){
        quantityDtos.stream().filter(dto -> dto.getQuantity() <= 0L)
                .map(ItemStock::getItemId)
                .findFirst()
                .ifPresent(itemId -> {
                    throw new CustomException(ErrorCode.ITEM_QUANTITY_ZERO);
                });
    }
    public Long priceCheck(Long point, List<Item> items){
        Long totalPrice = items.stream().mapToLong(Item::getPrice).sum();
        if(point-totalPrice<0) {
            throw new CustomException(ErrorCode.LACK_OF_USER_POINT);
        }
        return totalPrice;
    }

    /**
     *
     * @param orderRequestDto 주문요청 requestBody
     * @param itemStock 주문하려는 상품의 재고 정보
     * @return
     */
    public Long orderQuantityCheck(OrderRequestDto orderRequestDto, ItemStock itemStock){
        Long itemCnt = orderRequestDto.getItems().stream()
                .filter(dto -> dto.getItemId()==itemStock.getItemId())
                .map(OrderItemDto::getItemCount)
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.ITEM_NOT_FOUND));
        if(itemStock.getQuantity()<itemCnt){
            throw new CustomException(ErrorCode.LACK_OF_ITEM_QUANTITY);
        }else {
            return itemCnt;
        }
    }
}
