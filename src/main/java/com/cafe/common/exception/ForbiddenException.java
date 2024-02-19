package com.cafe.common.exception;

public class ForbiddenException extends MyCafeException {
    public ForbiddenException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
