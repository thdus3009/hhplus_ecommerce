package com.ecommerce.common.exception;

public record ErrorResponse(
	String code,
	String message
) {
}
