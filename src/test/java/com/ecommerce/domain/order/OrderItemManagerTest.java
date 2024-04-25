package com.ecommerce.domain.order;

import com.ecommerce.item.entity.Item;
import com.ecommerce.order.api.dto.OrderAndOrderItems;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.order.domain.infrastructure.OrderItemRepository;
import com.ecommerce.order.domain.infrastructure.OrderRepository;
import com.ecommerce.order.domain.service.OrderItemManager;
import com.ecommerce.order.domain.service.OrderManager;
import com.ecommerce.order.entity.Order;
import com.ecommerce.stub.StubData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

public class OrderItemManagerTest {
    private OrderItemRepository orderItemRepository;
    private OrderItemManager orderItemManager;
    @BeforeEach
    void setUp(){
        orderItemRepository = mock(OrderItemRepository.class);
        orderItemManager = new OrderItemManager(orderItemRepository);
    }

    @Test
    @DisplayName("OrderItem(주문 상품)들 저장")
    void save(){
        // given
        OrderAndOrderItems orderAndOrderItems = StubData.order(1L, 5000L);
        List<Item> items = StubData.items(List.of(1L));
        List<OrderItemDto> itemDtos = List.of(
                new OrderItemDto(
                        items.get(0).getId(),
                        items.get(0).getPrice(),
                        1L
                )
        );
        given(orderItemRepository.saveAll(any())).willReturn(orderAndOrderItems.getOrderItems());
        // when
        orderItemManager.save(orderAndOrderItems.getOrder(), items, itemDtos);
        // then
        // orderItemRepository 적어도 1번 호출 되었는지 확인
        verify(orderItemRepository, atLeast(1)).saveAll(any());
    }
}
