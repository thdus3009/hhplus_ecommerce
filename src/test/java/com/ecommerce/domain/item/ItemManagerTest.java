package com.ecommerce.domain.item;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.api.dto.ItemRequestDto;
import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.item.domain.infrastructure.ItemRepository;
import com.ecommerce.item.domain.service.ItemManager;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.stub.StubData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ItemManagerTest {
    private ItemRepository itemRepository;
    private ItemManager itemManager;
    @BeforeEach
    void setUp(){
        itemRepository = mock(ItemRepository.class);
        itemManager = new ItemManager(itemRepository);
    }

    @Test
    @DisplayName("특정 상품 조회")
    void getItem(){
        // given
        Long itemId = 1L;
        given(itemRepository.findById(anyLong())).willReturn(
                Optional.of(new Item(itemId, "abc 후드티", 15000L, 1L, ZonedDateTime.now()))
        );
        // when
        Item item = itemManager.getItem(itemId);
        // then
        Assertions.assertThat(item.getId()).isEqualTo(itemId);
    }

    @Test
    @DisplayName("특정 상품 조회시 해당 상품이 없을 때")
    void getNotExistItem(){
        // given
        Long itemId = 1L;
        given(itemRepository.findById(anyLong())).willReturn(Optional.empty());
        // when & then
        Assertions.assertThatThrownBy(()-> itemManager.getItem(itemId))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.ITEM_NULL+" -id : "+itemId);
    }

    @Test
    @DisplayName("OrderUseCase 에서 사용하는 주문할 상품정보들 조회")
    void getItemsFromOrder(){
        // given
        List<Long> itemIds = List.of(1L, 2L);
        given(itemRepository.findByIds(anyList())).willReturn(StubData.items(itemIds));
        // when
        List<Item> items = itemManager.getItemsFromOrder(itemIds);
        // then
        Assertions.assertThat(items).isNotNull();
        Assertions.assertThat(items.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(items.get(1).getId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("인기 상품 조회")
    void findItems(){
        // given
        Long date = 3L;
        Long count = 5L;
        ItemRequestDto requestDto = new ItemRequestDto(date, count);
        ZonedDateTime startDate = ZonedDateTime.now().minus(date, ChronoUnit.DAYS);
        List<PopularItemResponseDto> popularItemResponseDtos = List.of(
                new PopularItemResponseDto(1L, "상품1", 6000L, 34L),
                new PopularItemResponseDto(2L, "상품2", 5000L, 23L)
        );
        given(itemRepository.findItems(any(), anyLong())).willReturn(popularItemResponseDtos);

        // when
        List<PopularItemResponseDto> responseDtos = itemManager.findItems(requestDto);
        // then
        Assertions.assertThat(responseDtos).isNotNull();
        Assertions.assertThat(responseDtos.size()).isEqualTo(2);
        Assertions.assertThat(responseDtos.get(0).getName()).isEqualTo("상품1");
        Assertions.assertThat(responseDtos.get(1).getName()).isEqualTo("상품2");
    }

    @Test
    @DisplayName("상품 재고(ItemStock) 차감")
    void updateItemQuantity(){

        // given
        List<Item> items = StubData.items(List.of(1L));
        List<ItemStock> itemStocks = StubData.itemStocks(List.of(1L));
        // when
        itemManager.updateItemQuantity(items, itemStocks);

        // then
        // itemRepository가 적어도 1번 호출 되었는지 확인
        verify(itemRepository, atLeast(1)).saveAll(any());
    }
}
