package com.cafe.common.exception;

public class DuplicatedResourceException extends MyCafeException {

    public DuplicatedResourceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
