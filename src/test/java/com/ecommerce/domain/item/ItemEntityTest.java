package com.ecommerce.domain.item;

import java.time.ZonedDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ecommerce.item.entity.Item;

public class ItemEntityTest {
	private Item item;

	@BeforeEach
	void Setup() {
		item = new Item(1L, "abc 후드티", 15000L, 35L, ZonedDateTime.now());
	}

	@Test
	@DisplayName("재고 수정 테스트")
	void decreaseQuantity() {
		// given
		Long quantity = 30L;
		Long origin = item.getQuantity();
		// when
		item.updateQuantity(quantity);
		// then
		Assertions.assertThat(item.getQuantity()).isEqualTo(quantity);
	}
}
