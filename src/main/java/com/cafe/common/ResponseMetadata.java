package com.cafe.common;

import com.cafe.exception.ErrorCode;

public record ResponseMetadata(
        int code,
        String message
) {

    public static ResponseMetadata success() {
        return new ResponseMetadata(200, "ok");
    }

    public static ResponseMetadata from(ErrorCode errorCode) {
        return new ResponseMetadata(errorCode.getCode(), errorCode.getMessage());
    }

}
