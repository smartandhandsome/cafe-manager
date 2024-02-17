package com.cafe.exception;

public class DuplicatedResourceException extends MyCafeException {

    public DuplicatedResourceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
