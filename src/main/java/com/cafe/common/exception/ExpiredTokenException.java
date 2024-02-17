package com.cafe.common.exception;

public class ExpiredTokenException extends MyCafeException {
    public ExpiredTokenException(ErrorCode errorCode, String message, Throwable e) {
        super(errorCode, message, e);
    }
}
