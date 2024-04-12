package com.ecommerce.common.exception;

import org.springframework.stereotype.Component;

@Component
public class ErrorCode {
    public static final String USER_POINT_NULL = "UserPoint 객체가 null입니다.";
    public static final String ITEM_NULL = "Item 객체가 null입니다.";
    public static final String ITEM_QUANTITY_ZERO = "특정 상품이 품절입니다.";
    public static final String LACK_OF_USER_POINT = "포인트가 부족합니다.";
    public static final String LACK_OF_ITEM_QUANTITY = "해당 상품의 재고가 부족합니다.";
    public static final String ITEM_NOT_FOUND = "해당 상품의 정보를 확인할 수 없습니다.";
}
