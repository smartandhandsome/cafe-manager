package com.cafe.common.exception;

public class NotSupportedSearchStrategyException extends MyCafeException {
    public NotSupportedSearchStrategyException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
