package com.ecommerce.item.entity;

import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import com.ecommerce.item.api.dto.PopularItemResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "popularItems")
public class PopularItems {
	private List<PopularItemResponseDto> items;
	private String createdAt;
}
