package com.ecommerce.domain.item;

import com.ecommerce.item.api.dto.ItemRequestDto;
import com.ecommerce.item.api.dto.ItemResponseDto;
import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.item.domain.service.ItemManager;
import com.ecommerce.item.domain.service.ItemService;
import com.ecommerce.item.entity.Item;
import com.ecommerce.stub.StubData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ItemService.class)
public class ItemServiceTest {
    @MockBean
    private ItemManager itemManager;
    private ItemService itemService;

    @BeforeEach
    void setUp(){
        itemService = new ItemService(itemManager);
    }

    @Test
    @DisplayName("특정 상품 조회")
    void getItem(){
        // given
        Long itemId = 1L;
        given(itemManager.getItem(anyLong())).willReturn(
                new Item(itemId, "abc 후드티", 15000L, 1L, ZonedDateTime.now())
        );
        // when
        ItemResponseDto responseDto = itemService.getItem(itemId);
        // then
        Assertions.assertThat(responseDto.getId()).isEqualTo(itemId);
    }

    @Test
    @DisplayName("OrderUseCase 에서 사용하는 주문할 상품정보들 조회")
    void getItemsFromOrder(){
        // given
        List<Long> itemIds = List.of(1L, 2L);
        given(itemManager.getItemsFromOrder(anyList())).willReturn(StubData.items(itemIds));
        // when
        List<Item> items = itemService.getItemsFromOrder(itemIds);
        // then
        Assertions.assertThat(items).isNotNull();
        Assertions.assertThat(items.size()).isEqualTo(2);
        Assertions.assertThat(items.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(items.get(1).getId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("인기 상품 조회")
    void findItems(){
        // given
        ItemRequestDto requestDto = new ItemRequestDto(3L, 5L);
        List<PopularItemResponseDto> popularItemResponseDtos = List.of(
                new PopularItemResponseDto(1L, "상품1", 6000L, 34L),
                new PopularItemResponseDto(2L, "상품2", 5000L, 23L)
        );
        given(itemManager.findItems(any())).willReturn(popularItemResponseDtos);
        // when
        List<PopularItemResponseDto> responseDtos = itemService.findItems(requestDto);
        // then
        Assertions.assertThat(responseDtos).isNotNull();
        Assertions.assertThat(responseDtos.size()).isEqualTo(2);
        Assertions.assertThat(responseDtos.get(0).getName()).isEqualTo("상품1");
        Assertions.assertThat(responseDtos.get(1).getName()).isEqualTo("상품2");
    }
}
