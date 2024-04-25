package com.ecommerce.userPoint.api.controller;

import com.ecommerce.userPoint.api.dto.PointDto;
import com.ecommerce.userPoint.api.dto.PointResponseDto;
import com.ecommerce.userPoint.domain.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/points")
@RestController
public class PointController {

    private final PointService pointService;

    @Operation(
        summary = "포인트 충전",
        description = "사용자의 포인트를 충전한다.",
        tags = "포인트"
    )
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{user_id}/charge", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PointResponseDto> chargePoint(
            @PathVariable(value = "user_id", required = true) Long userId,
            @RequestBody @Valid PointDto pointDto
    ){
        return ResponseEntity.ok().body(
                pointService.chargePoint(userId, pointDto.getPoint())
        );

    }
    @Operation(
            summary = "포인트 조회",
            description = "사용자의 포인트를 조회한다.",
            tags = "포인트"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PointResponseDto> getPoint(
            @PathVariable(value = "user_id", required = true) Long userId
    ){
        return ResponseEntity.ok().body(
                pointService.getPoint(userId)
        );
    }
}
