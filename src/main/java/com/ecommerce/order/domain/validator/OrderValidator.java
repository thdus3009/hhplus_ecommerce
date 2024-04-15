package com.ecommerce.order.domain.validator;

import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.ItemStock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderValidator {

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
