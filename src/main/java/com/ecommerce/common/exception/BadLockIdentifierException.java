package com.ecommerce.common.exception;

public class BadLockIdentifierException extends RuntimeException {
	public BadLockIdentifierException() {
		super(ErrorCode.BAD_LOCK_IDENTIFIER);
	}
}
