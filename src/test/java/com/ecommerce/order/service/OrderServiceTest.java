//package com.ecommerce.order.service;
//
//import com.ecommerce.order.api.dto.OrderItemDto;
//import com.ecommerce.order.api.dto.OrderRequestDto;
//import com.ecommerce.item.domain.infrastructure.ItemRepository;
//import com.ecommerce.item.domain.infrastructure.ItemStockRepository;
//import com.ecommerce.order.domain.infrastructure.OrderItemRepository;
//import com.ecommerce.order.domain.infrastructure.OrderRepository;
//import com.ecommerce.order.domain.service.OrderService;
//import com.ecommerce.order.domain.validator.OrderValidator;
//import com.ecommerce.userPoint.domain.repository.UserPointRepository;
//import com.ecommerce.order.stub.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest(classes = OrderServiceTest.class)
//public class OrderServiceTest {
//    OrderService orderService;
//    UserPointRepository userPointRepository;
//    ItemRepository itemRepository;
//    ItemStockRepository itemStockRepository;
//    OrderRepository orderRepository;
//    OrderItemRepository orderItemRepository;
//    OrderValidator orderValidator;
//
//    public OrderServiceTest(){
//        userPointRepository = new UserPointRepositoryStub();
//        itemRepository = new ItemRepositoryStub();
//        itemStockRepository = new ItemStockRepositoryStub();
//        orderRepository = new OrderRepositoryStub();
//        orderItemRepository = new OrderItemRepositoryStub();
//        orderValidator = new OrderValidator();
//        orderService = new OrderService(
//                orderRepository,
//                orderItemRepository,
//                itemStockRepository,
//                userPointRepository,
//                itemRepository,
//                orderValidator
//        );
//    }
//    @BeforeEach
//    public void beforeEach(){
//
//    }
//
//    OrderRequestDto orderRequestDto = OrderRequestDto.builder()
//            .userId(1L)
//            .items(List.of(
//                new OrderItemDto(1L, 3L),
//                new OrderItemDto(2L, 1L),
//                new OrderItemDto(3L, 2L)
//                ))
//            .receiverName("김소연")
//            .receiverPhone("010-1111-1111")
//            .receiverAddress("강남구 언주로 100길")
//            .build();
//
//    @Test
//    public void 실패(){
//
//    }
//}
