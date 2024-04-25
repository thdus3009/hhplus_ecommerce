package com.ecommerce.item.api.dto;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본생성자 추가
public class ItemRequestDto {
    @Positive
    private Long date; // 오늘을 기준으로 조회할 날짜 (ex. 오늘이 2/7, date가 3이면, 2/4 ~ 오늘날짜로 조회된다.)
    @Positive
    private Long count;

    public ItemRequestDto(Long date, Long count){
        this.date = date;
        this.count = count;
    }
}
