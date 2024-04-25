package com.ecommerce.domain.item.stock;
import com.ecommerce.item.domain.service.ItemService;
import com.ecommerce.item.domain.service.ItemStockManager;
import com.ecommerce.item.domain.service.ItemStockService;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.stub.StubData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
@WebMvcTest(ItemStockServiceTest.class)
public class ItemStockServiceTest {
    @MockBean
    private ItemStockManager itemStockManager;
    private ItemStockService itemStockService;

    @BeforeEach
    void setUp(){
        itemStockService = new ItemStockService(itemStockManager);
    }

    @Test
    @DisplayName("해당 상품 재고 있는지 확인")
    void checkByIds(){
        // given
        List<Long> itemIds = List.of(1L, 2L);
        given(itemStockManager.checkByIds(anyList())).willReturn(StubData.itemStocks(itemIds));
        // when
        List<ItemStock> itemStocks = itemStockService.checkByIds(itemIds);
        // then
        Assertions.assertThat(itemStocks).isNotNull();
        Assertions.assertThat(itemStocks.get(0).getItemId()).isEqualTo(1L);
        Assertions.assertThat(itemStocks.get(1).getItemId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("상품 재고 수량 차감")
    void decreaseQuantity(){
        // given
        List<Long> itemIds = List.of(1L, 2L);
        List<ItemStock> itemStocks = StubData.itemStocks(itemIds);
        List<OrderItemDto> itemDtos = List.of(
            new OrderItemDto(1L, 5000L, 1L),
            new OrderItemDto(2L, 4000L, 1L)
        );
        given(itemStockManager.updateStock(anyList(), anyList())).willReturn(itemStocks);
        // when
        List<ItemStock> res = itemStockService.decreaseQuantity(itemStocks, itemDtos);
        // then
        Assertions.assertThat(res).isNotNull();
        Assertions.assertThat(res.size()).isEqualTo(2);
        Assertions.assertThat(res.get(0).getItemId()).isEqualTo(1L);
        Assertions.assertThat(res.get(1).getItemId()).isEqualTo(2L);
    }
}
