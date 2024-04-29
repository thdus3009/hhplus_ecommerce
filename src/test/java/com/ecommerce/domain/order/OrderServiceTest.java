package com.ecommerce.domain.order;

import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.OrderItemStatus;
import com.ecommerce.order.api.dto.OrderAndOrderItems;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.domain.service.OrderItemManager;
import com.ecommerce.order.domain.service.OrderManager;
import com.ecommerce.order.domain.service.OrderService;
import com.ecommerce.order.domain.validator.OrderValidator;
import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.stub.StubData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(OrderService.class)
public class OrderServiceTest {
    @MockBean
    private OrderManager orderManager;
    @MockBean
    private OrderItemManager orderItemManager;
    @MockBean
    private OrderValidator orderValidator;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderManager, orderItemManager, orderValidator);
    }

    @Test
    @DisplayName("주문 내역 추가")
    void order() {
        // given
        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .userId(1L)
                .items(List.of(new OrderItemDto(1L, 5000L, 1L)))
                .totalPrice(5000L)
                .receiverName("김소연")
                .receiverPhone("01011112222")
                .receiverAddress("서울특별시 강남구")
                .build();
        List<Item> items = StubData.items(List.of(1L));
        OrderAndOrderItems orderAndOrderItems = StubData.order(1L, 5000L);

        given(orderManager.save(any())).willReturn(orderAndOrderItems.getOrder());
        given(orderItemManager.save(any(), any(), any())).willReturn(
                List.of(
                    new OrderItem(
                            orderAndOrderItems.getOrder(), items.get(0), OrderItemStatus.READY,null,"abc 후드티",1L,5000L
                    )
                )
        );
        // when
        OrderAndOrderItems res = orderService.order(orderRequestDto,items);
        // then
        Assertions.assertThat(res).isNotNull();
        Assertions.assertThat(res.getOrder()).isEqualTo(orderAndOrderItems.getOrder());
    }
}
