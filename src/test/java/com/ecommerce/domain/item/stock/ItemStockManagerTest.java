package com.ecommerce.domain.item.stock;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ecommerce.item.domain.infrastructure.ItemStockRepository;
import com.ecommerce.item.domain.service.ItemStockManager;
import com.ecommerce.item.domain.validator.ItemValidator;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.stub.StubData;

public class ItemStockManagerTest {
	private ItemStockRepository itemStockRepository;
	private ItemValidator itemValidator;
	private ItemStockManager itemStockManager;

	@BeforeEach
	void setUp() {
		itemStockRepository = mock(ItemStockRepository.class);
		itemValidator = mock(ItemValidator.class);
		itemStockManager = new ItemStockManager(itemStockRepository, itemValidator);
	}

	@Test
	@DisplayName("해당 상품 재고 있는지 확인")
	void checkByIds() {
		// given
		List<Long> itemIds = List.of(1L, 2L);
		given(itemStockRepository.findByItemIds(anyList())).willReturn(StubData.itemStocks(itemIds));
		// when
		List<ItemStock> itemStocks = itemStockManager.checkByIds(itemIds);
		// then
		Assertions.assertThat(itemStocks).isNotNull();
		Assertions.assertThat(itemStocks.get(0).getItemId()).isEqualTo(1L);
		Assertions.assertThat(itemStocks.get(1).getItemId()).isEqualTo(2L);
	}

	@Test
	@DisplayName("상품 재고 수량 차감")
	void updateStock() {
		// given
		List<Long> itemIds = List.of(1L, 2L);
		List<ItemStock> itemStocks = StubData.itemStocks(itemIds);
		List<OrderItemDto> itemDtos = List.of(
			new OrderItemDto(1L, 5000L, 1L),
			new OrderItemDto(2L, 4000L, 1L)
		);
		// when
		itemStockManager.updateStock(itemStocks, itemDtos);
		// then
		// itemRepository가 적어도 1번 호출 되었는지 확인
		verify(itemStockRepository, atLeast(1)).saveAll(any());
	}
}
