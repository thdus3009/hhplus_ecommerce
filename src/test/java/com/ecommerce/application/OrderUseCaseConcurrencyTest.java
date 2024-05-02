package com.ecommerce.application;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.item.api.dto.ItemResponseDto;
import com.ecommerce.item.domain.service.ItemManager;
import com.ecommerce.item.domain.service.ItemService;
import com.ecommerce.item.domain.service.ItemStockManager;
import com.ecommerce.item.domain.service.ItemStockService;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.domain.service.OrderUseCase;
import com.ecommerce.userpoint.domain.service.PointService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class OrderUseCaseConcurrencyTest {

	@Autowired
	private OrderUseCase orderUseCase;

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemStockService itemStockService;
	@Autowired
	private PointService pointService;

	@Autowired
	private ItemManager itemManager;
	@Autowired
	private ItemStockManager itemStockManager;

	@Test
	@Order(1)
	@Transactional
	@DisplayName("주문 동시성 테스트를 위한 데이터 추가")
	void addInfo() {
		int userCount = 5;
		Long itemId = 1L;
		Item item = itemManager.getItem(1L);
		ItemStock itemStock = itemStockManager.getItemStock(1L);

		// 테스트하기 위한 데이터 추가
		for (int i = 1; i <= userCount; i++) {
			Long userId = Long.valueOf(i);
			pointService.chargePoint(userId, 5000L);
		}
		// ??
		ItemStock res2 = itemStockManager.save(itemStock, itemStock.getQuantity() + 5L).get(0);
		Item res1 = itemManager.save(item, item.getQuantity() + 5L).get(0);
	}

	@Test
	@Order(2)
	@Transactional
	@DisplayName("주문 동시성 테스트")
	void orderTest() throws Exception {
		// given
		int userCount = 5;
		Long itemId = 1L;

		List<OrderItemDto> orderItemDtos = List.of(
			new OrderItemDto(itemId, 5000L, 1L)
		);
		CountDownLatch latch = new CountDownLatch(userCount);
		ExecutorService executorService = Executors.newFixedThreadPool(userCount);

		// when
		for (int i = 1; i <= userCount; i++) {
			Long userId = Long.valueOf(i);
			OrderRequestDto orderRequestDto = OrderRequestDto.builder()
				.userId(userId)
				.items(orderItemDtos)
				.totalPrice(5000L)
				.receiverName("회원" + i)
				.receiverPhone("01011112222")
				.receiverAddress("주소" + i)
				.build();

			executorService.submit(() -> {
				try {
					orderUseCase.order(orderRequestDto);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					latch.countDown();
				}
			});
		}
		latch.await();
		executorService.shutdown();

		ItemResponseDto itemResponseDto = itemService.getItem(1L);
		List<ItemStock> itemStocks = itemStockService.checkByIds(List.of(1L));

		Assertions.assertThat(itemResponseDto.getQuantity()).isEqualTo(itemStocks.get(0).getQuantity());
	}

}
