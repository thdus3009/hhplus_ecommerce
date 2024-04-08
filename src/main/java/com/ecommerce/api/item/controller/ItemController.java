package com.ecommerce.api.item.controller;

import com.ecommerce.api.item.dto.ItemResponseDto;
import com.ecommerce.api.point.dto.PointResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@RequestMapping("/items")
@RestController
public class ItemController {
    @Operation(
            summary = "상품 조회",
            description = "상품 정보를 조회한다.",
            tags = "상품"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{item_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemResponseDto> getItem(
            @PathVariable(value = "item_id", required = true) Long itemId
    ){
        ItemResponseDto result = ItemResponseDto.builder()
                .id(0L)
                .name("[2+1] xoxo 맨투맨")
                .price(15000L)
                .itemCount(35L)
                .createdAt(ZonedDateTime.now())
                .updatedAt(null)
                .build();

        return ResponseEntity.ok().body(result);
    }
}
