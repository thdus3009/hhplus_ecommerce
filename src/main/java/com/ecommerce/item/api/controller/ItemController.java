package com.ecommerce.item.api.controller;

import com.ecommerce.item.api.dto.ItemRequestDto;
import com.ecommerce.item.api.dto.ItemResponseDto;
import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.item.domain.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/items")
@RestController
public class ItemController {
    private final ItemService itemService;
    @Operation(
            summary = "상품 조회",
            description = "상품 정보를 조회한다.",
            tags = "상품"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{item_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemResponseDto> findItem(
            @PathVariable(value = "item_id", required = true) Long itemId
    ){
        return ResponseEntity.ok().body(
                itemService.getItem(itemId)
        );
    }

    @Operation(
            summary = "인기 상품 조회",
            description = "인기 상품을 조회한다.",
            tags = "상품"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PopularItemResponseDto>> findItems(
            @RequestBody @Valid ItemRequestDto itemRequestDto
    ){
        return ResponseEntity.ok().body(
            itemService.findItems(itemRequestDto)
        );
    }
}
