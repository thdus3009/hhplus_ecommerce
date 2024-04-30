package com.ecommerce.domain.order;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.domain.infrastructure.OrderRepository;
import com.ecommerce.order.domain.service.OrderManager;
import com.ecommerce.order.entity.Order;
import com.ecommerce.stub.StubData;

public class OrderManagerTest {
	private OrderRepository orderRepository;
	private OrderManager orderManager;

	@BeforeEach
	void setUp() {
		orderRepository = mock(OrderRepository.class);
		orderManager = new OrderManager(orderRepository);
	}

	@Test
	@DisplayName("Order(주문) 저장")
	void save() {
		// given
		OrderRequestDto requestDto = OrderRequestDto.builder()
			.userId(1L)
			.items(List.of(new OrderItemDto(1L, 5000L, 1L)))
			.totalPrice(5000L)
			.receiverName("김소연")
			.receiverPhone("01011112222")
			.receiverAddress("서울특별시 강남구")
			.build();
		Order order = StubData.order(1L, 5000L).getOrder();
		given(orderRepository.save(any())).willReturn(order);
		// when
		Order res = orderManager.save(requestDto);
		// then
		Assertions.assertThat(res.getTotalPrice()).isEqualTo(requestDto.getTotalPrice());
	}
}
