package com.ecommerce.api.order.controller;

import com.ecommerce.api.item.dto.ItemResponseDto;
import com.ecommerce.api.order.dto.OrderRequestDto;
import com.ecommerce.api.order.dto.OrderResponseDto;
import com.ecommerce.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController {
    private final OrderService orderService;
    @Operation(
            summary = "상품 주문",
            description = "상품들을 주문/결제한다.",
            tags = "주문/결제"
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseDto> getItem(
            @RequestBody @Valid OrderRequestDto orderRequestDto
    ){
        return ResponseEntity.ok().body(
                orderService.save(orderRequestDto));
    }
}
