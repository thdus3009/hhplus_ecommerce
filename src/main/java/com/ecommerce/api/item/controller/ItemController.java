package com.ecommerce.api.item.controller;

import com.ecommerce.api.item.dto.ItemResponseDto;
import com.ecommerce.api.order.dto.OrderRequestDto;
import com.ecommerce.api.point.dto.PointResponseDto;
import com.ecommerce.domain.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
    private final ItemService itemService;
    @Operation(
            summary = "상품 조회",
            description = "상품 정보를 조회한다.",
            tags = "상품"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{item_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemResponseDto> getItem(
            @PathVariable(value = "item_id", required = true) Long itemId,
            @RequestBody @Valid OrderRequestDto orderRequestDto
            ){
        return ResponseEntity.ok().body(
                itemService.getItem(itemId, orderRequestDto)
        );
    }
}
