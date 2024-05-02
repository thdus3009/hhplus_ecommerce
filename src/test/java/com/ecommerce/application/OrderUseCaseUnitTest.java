package com.ecommerce.application;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.domain.service.ItemService;
import com.ecommerce.item.domain.service.ItemStockService;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderAndOrderItems;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.api.dto.OrderResponseDto;
import com.ecommerce.order.domain.service.OrderService;
import com.ecommerce.order.domain.service.OrderUseCase;
import com.ecommerce.stub.StubData;
import com.ecommerce.userpoint.domain.service.PointService;
import com.ecommerce.userpoint.entity.UserPoint;

@WebMvcTest(OrderUseCase.class)
public class OrderUseCaseUnitTest {

	@MockBean
	private PointService pointService;
	@MockBean
	private OrderService orderService;
	@MockBean
	private ItemService itemService;
	@MockBean
	private ItemStockService itemStockService;
	@MockBean
	private ApplicationEventPublisher applicationEventPublisher;

	private OrderUseCase orderUseCase;

	@BeforeEach
	void setUp() {
		orderUseCase = new OrderUseCase(pointService, orderService, itemService, itemStockService,
			applicationEventPublisher);
	}

	@Test
	@DisplayName("주문 성공")
	void order() throws Exception {
		// given
		Long userId = 1L;
		Long totalPrice = 5000L;
		UserPoint userPoint = StubData.usePoint(userId);
		List<ItemStock> itemStocks = StubData.itemStocks(List.of(1L));
		OrderAndOrderItems orderAndOrderItems = StubData.order(userId, totalPrice);

		OrderRequestDto request = OrderRequestDto.builder()
			.userId(userId)
			.items(List.of(
				new OrderItemDto(1L, 5000L, 1L)
			))
			.totalPrice(totalPrice)
			.receiverName("김소연")
			.receiverPhone("01011112222")
			.receiverAddress("서울특별시 강남구")
			.build();

		given(itemStockService.checkByIds(anyList())).willReturn(itemStocks);
		given(pointService.usePoint(anyLong(), anyLong())).willReturn(userPoint);
		given(orderService.order(any(), anyList())).willReturn(orderAndOrderItems);

		// when
		OrderResponseDto orderResponseDto = orderUseCase.order(request);

		// then
		Assertions.assertThat(orderResponseDto).isNotNull();
		Assertions.assertThat(orderResponseDto.getOrder().getUserId()).isEqualTo(userId);
		Assertions.assertThat(orderResponseDto.getOrder().getTotalPrice()).isEqualTo(totalPrice);
	}

	@Test
	@DisplayName("request에 주문상품이 안들어 있을 경우")
	void orderNotItems() {
		Long userId = 1L;
		Long totalPrice = 5000L;
		UserPoint userPoint = StubData.usePoint(userId);
		List<ItemStock> itemStocks = StubData.itemStocks(List.of(1L));
		OrderAndOrderItems orderAndOrderItems = StubData.order(userId, totalPrice);

		OrderRequestDto request = OrderRequestDto.builder()
			.userId(userId)
			.items(List.of())
			.totalPrice(totalPrice)
			.receiverName("김소연")
			.receiverPhone("01011112222")
			.receiverAddress("서울특별시 강남구")
			.build();
		given(itemStockService.checkByIds(anyList())).willReturn(itemStocks);
		given(pointService.usePoint(anyLong(), anyLong())).willReturn(userPoint);
		given(orderService.order(any(), anyList())).willReturn(orderAndOrderItems);

		// when & then
		Assertions.assertThatThrownBy(() -> orderUseCase.order(request))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.ITEM_NULL);
	}

}
