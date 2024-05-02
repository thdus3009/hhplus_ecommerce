package com.ecommerce.common.exception;

import org.springframework.stereotype.Component;

@Component
public class ErrorCode {
	public static final String USER_POINT_NULL = "UserPoint 객체가 null입니다.";
	public static final String ITEM_NULL = "해당 상품의 정보를 찾지 못하였습니다.";
	public static final String ITEM_STOCK_NULL = "해당 상품의 재고 정보를 찾지 못하였습니다.";
	public static final String ITEM_QUANTITY_ZERO = "특정 상품이 품절입니다.";
	public static final String LACK_OF_USER_POINT = "포인트가 부족합니다.";
	public static final String LACK_OF_ITEM_QUANTITY = "해당 상품의 재고가 부족합니다.";
	public static final String ITEM_NOT_FOUND = "해당 상품의 정보를 확인할 수 없습니다.";
	public static final String ORDER_ITEM_NOT_FOUND = "주문 상품에 해당하는 상품정보가 없습니다.";
	public static final String LOCK_NOT_AVAILABLE = "해당 락이 존재하지 않습니다.";
	public static final String LOCK_INTERRUPTED_ERROR = "";
	public static final String BAD_LOCK_IDENTIFIER = "락의 키값이 잘못 세팅 되었습니다.";
}
