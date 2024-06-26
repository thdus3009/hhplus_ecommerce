package com.ecommerce.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.ZonedDateTime;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.OrderItemStatus;
import com.ecommerce.order.api.controller.OrderController;
import com.ecommerce.order.api.dto.OrderResponseDto;
import com.ecommerce.order.domain.service.OrderUseCase;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderUseCase orderUseCase;

	@Test
	@DisplayName("상품 주문 요청 성공")
	void order() throws Exception {
		OrderResponseDto orderResponseDto = mkOrderResponseDto();
		// given
		given(orderUseCase.order(any()))
			.willReturn(orderResponseDto);

		// when
		MvcResult mvcResult = mockMvc.perform(post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
					  "userId" : 1,
					  "items" : [
					    {
					        "itemId" : 1,
					        "itemPrice" : 35000,
					        "itemCount" : 1
					    }
					  ],
					  "totalPrice" : 35000,
					  "receiverName" : "김소연",
					  "receiverPhone" : "01011112222",
					  "receiverAddress" : "서울특별시 강남구"
					}
					"""))
			.andExpect(status().isOk())
			.andReturn();

		// then
		String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(content);
		assertEquals(
			jsonObject.getJSONObject("order").getString("uuid"),
			orderResponseDto.getOrder().getUuid()
		);
	}

	@Test
	@DisplayName("총 상품 주문 금액이 0일때도 성공 > 이벤트나 할인혜택")
	void orderTotalPriceZero() throws Exception {
		OrderResponseDto orderResponseDto = mkOrderResponseDto();
		// given
		given(orderUseCase.order(any()))
			.willReturn(orderResponseDto);

		// when
		MvcResult mvcResult = mockMvc.perform(post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
					  "userId" : 1,
					  "items" : [
					    {
					        "itemId" : 1,
					        "itemPrice" : 0,
					        "itemCount" : 1
					    }
					  ],
					  "totalPrice" : 0,
					  "receiverName" : "김소연",
					  "receiverPhone" : "01011112222",
					  "receiverAddress" : "서울특별시 강남구"
					}
					"""))
			.andExpect(status().isOk())
			.andReturn();

		// then
		String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(content);
		assertEquals(
			jsonObject.getJSONObject("order").getString("uuid"),
			orderResponseDto.getOrder().getUuid()
		);
	}

	@Test
	@DisplayName("받는사람 정보 미입력시 주문 요청 실패")
	void orderNotReceiver() throws Exception {
		OrderResponseDto orderResponseDto = mkOrderResponseDto();
		// given
		given(orderUseCase.order(any()))
			.willReturn(orderResponseDto);

		// when
		mockMvc.perform(post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
					  "userId" : 1,
					  "items" : [
					    {
					        "itemId" : 1,
					        "itemPrice" : 0,
					        "itemCount" : 1
					    }
					  ],
					  "totalPrice" : 0,
					  "receiverName" : "",
					  "receiverPhone" : "",
					  "receiverAddress" : ""
					}
					"""))
			.andExpect(status().isBadRequest());

		// then
		// orderUseCase가 한번도 호출되지 않았는지 검증
		verify(orderUseCase, never()).order(any());
	}

	OrderResponseDto mkOrderResponseDto() {

		Order order = new Order("asdf1234", 1L, 35000L, 1L, "김소연", "01011112222", "서울특별시 강남구");
		;
		Item item = new Item(1L, "상품1", 5000L, 1L, ZonedDateTime.now());
		List<OrderItem> orderItems = List.of(
			new OrderItem(
				order, item, OrderItemStatus.READY, null, "abc 후드티", 1L, 35000L
			)
		);

		return OrderResponseDto.of(order, orderItems);
	}
}
