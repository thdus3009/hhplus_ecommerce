package com.ecommerce.api.point.controller;

import com.ecommerce.api.point.dto.PointDto;
import com.ecommerce.api.point.dto.PointResponseDto;
import com.ecommerce.domain.point.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

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
    @PostMapping(value = "/{user_id}/charge", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PointResponseDto> chargePoint(
            @PathVariable(value = "user_id", required = true) Long userId,
            @RequestBody @Valid PointDto pointDto
    ){
        return ResponseEntity.ok().body(
                pointService.chargePoints(userId, pointDto.getPoint())
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
        //PointResponseDto result = new PointResponseDto(0L,userId,1000L, ZonedDateTime.now(), null);
        PointResponseDto result = PointResponseDto.builder()
                .id(0L)
                .userId(userId)
                .point(1000L)
                .createdAt(ZonedDateTime.now())
                .updatedAt(null)
                .build();

        return ResponseEntity.ok().body(result);
    }
}
