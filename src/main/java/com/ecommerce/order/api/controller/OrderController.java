package com.ecommerce.order.api.controller;

import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.api.dto.OrderResponseDto;
import com.ecommerce.order.domain.service.OrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController {
    private final OrderUseCase orderUseCase;
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
                orderUseCase.order(orderRequestDto));
    }
}
