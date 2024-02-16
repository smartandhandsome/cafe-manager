package com.cafe.exception;

public class IllegalTokenException extends MyCafeException {

    public IllegalTokenException(ErrorCode errorCode, String message, Throwable e) {
        super(errorCode, message, e);
    }

}
