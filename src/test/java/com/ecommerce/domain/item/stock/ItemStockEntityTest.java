package com.ecommerce.domain.item.stock;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.entity.ItemStock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemStockEntityTest {
    private ItemStock itemStock;

    @BeforeEach
    void setUp(){
        itemStock = new ItemStock(1L, 5L);
    }

    @Test
    @DisplayName("재고 차감 테스트")
    void decreaseStock(){
        // given
        Long quantity = 3L;
        Long origin = itemStock.getQuantity();
        // when
        itemStock.decreaseStock(quantity);
        // then
        Assertions.assertThat(itemStock.getQuantity()).isEqualTo(origin - quantity);
    }
    @Test
    @DisplayName("재고 부족시 에러 발생")
    void checkEnoughItemStockQuantity(){
        // given
        Long quantity = 6L;
        Long origin = itemStock.getQuantity();
        // when & then
        Assertions.assertThatThrownBy(() ->  itemStock.checkEnoughItemStockQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("["+itemStock.getItemId()+"] 상품 재고가 부족합니다.");
    }
}
