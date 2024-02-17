package com.cafe.common.exception;

import lombok.Getter;

@Getter
public class MyCafeException extends RuntimeException {

    private final ErrorCode errorCode;

    public MyCafeException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MyCafeException(ErrorCode errorCode, String message, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
    }

}
