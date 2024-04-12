package com.ecommerce.order.service;

import com.ecommerce.api.order.dto.OrderItemDto;
import com.ecommerce.api.order.dto.OrderRequestDto;
import com.ecommerce.domain.item.infrastructure.ItemRepository;
import com.ecommerce.domain.item.infrastructure.ItemStockRepository;
import com.ecommerce.domain.order.infrastructure.OrderItemRepository;
import com.ecommerce.domain.order.infrastructure.OrderRepository;
import com.ecommerce.domain.order.service.OrderService;
import com.ecommerce.domain.order.validator.OrderValidator;
import com.ecommerce.domain.point.repository.UserPointRepository;
import com.ecommerce.domain.point.service.PointService;
import com.ecommerce.order.stub.*;
import com.ecommerce.point.stub.UserPointHistoryManagerStub;
import com.ecommerce.point.stub.UserPointManagerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = OrderServiceTest.class)
public class OrderServiceTest {
    OrderService orderService;
    UserPointRepository userPointRepository;
    ItemRepository itemRepository;
    ItemStockRepository itemStockRepository;
    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    OrderValidator orderValidator;

    public OrderServiceTest(){
        userPointRepository = new UserPointRepositoryStub();
        itemRepository = new ItemRepositoryStub();
        itemStockRepository = new ItemStockRepositoryStub();
        orderRepository = new OrderRepositoryStub();
        orderItemRepository = new OrderItemRepositoryStub();
        orderValidator = new OrderValidator();
        orderService = new OrderService(
                orderRepository,
                orderItemRepository,
                itemStockRepository,
                userPointRepository,
                itemRepository,
                orderValidator
        );
    }
    @BeforeEach
    public void beforeEach(){

    }

    OrderRequestDto orderRequestDto = OrderRequestDto.builder()
            .userId(1L)
            .items(List.of(
                new OrderItemDto(1L, 3L),
                new OrderItemDto(2L, 1L),
                new OrderItemDto(3L, 2L)
                ))
            .receiverName("김소연")
            .receiverPhone("010-1111-1111")
            .receiverAddress("강남구 언주로 100길")
            .build();

    @Test
    public void 실패(){

    }
}
