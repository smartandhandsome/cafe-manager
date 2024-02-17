package com.cafe.exception;

public class LoginFailException extends MyCafeException {

    public LoginFailException(ErrorCode errorCode, String message, Throwable e) {
        super(errorCode, message, e);
    }
}
