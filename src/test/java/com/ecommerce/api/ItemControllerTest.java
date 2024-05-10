package com.ecommerce.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.ZonedDateTime;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.ecommerce.item.api.controller.ItemController;
import com.ecommerce.item.api.dto.ItemResponseDto;
import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.item.domain.service.ItemService;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemService itemService;

	@Test
	@DisplayName("상품 조회 성공")
	void getItemDetail() throws Exception {
		Long itemId = 1L;
		ItemResponseDto itemDto = mkItemDto(itemId);
		// given
		given(itemService.getItem(itemId))
			.willReturn(itemDto);

		// when & then
		mockMvc.perform(get("/items/" + itemId))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.id").value(itemId)
			);

	}

	@Test
	@DisplayName("인기 상품 목록 조회 성공")
	void getPopularItemList() throws Exception {
		Long date = 3L;
		Long count = 5L;
		List<PopularItemResponseDto> popularItemDtos = mkPopularItemDto();
		// given
		given(itemService.findItems(anyLong(), anyLong()))
			.willReturn(popularItemDtos);

		// when
		MvcResult mvcResult = mockMvc.perform(get("/items")
				.contentType(MediaType.APPLICATION_JSON)
				.param("date", "3")  // "date" 파라미터 추가
				.param("count", "5"))  // "count" 파라미터 추가
			// .content("""
			// 	{
			// 	"date" : 3,
			// 	"count" : 5
			// 	}
			// 	"""))
			.andExpect(status().isOk())
			.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		JSONArray jsonArray = new JSONArray(content);

		// then
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Long itemId = jsonObject.getLong("itemId");
			assertEquals(itemId, popularItemDtos.get(i).getItemId());
		}
	}

	@Test
	@DisplayName("인기 상품 목록 조회 실패 > date, count 음수")
	void getPopularItemListFail() throws Exception {
		mockMvc.perform(get("/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
					"date" : -1,
					"count" : -1
					}
					"""))
			.andExpect(status().is4xxClientError());
	}

	ItemResponseDto mkItemDto(Long itemId) {
		return ItemResponseDto.builder()
			.id(itemId)
			.name("ABC 후드티")
			.price(34000L)
			.quantity(1L)
			.createdAt(ZonedDateTime.now())
			.updatedAt(null)
			.build();
	}

	List<PopularItemResponseDto> mkPopularItemDto() {
		PopularItemResponseDto item1 =
			new PopularItemResponseDto(1L, "ABC 후드티", 34000L, 54L);
		PopularItemResponseDto item2 =
			new PopularItemResponseDto(2L, "데님 슬링백", 23000L, 32L);
		PopularItemResponseDto item3 =
			new PopularItemResponseDto(3L, "레트로 포켓 맨투맨", 54000L, 13L);

		return List.of(item1, item2, item3);
	}

}
