package com.cafe.common.exception;

import com.cafe.common.ResponseMetadata;
import lombok.Getter;

@Getter
public class CafeException extends RuntimeException {

    private final ResponseMetadata metadata;

    public CafeException(ResponseMetadata metadata, String message) {
        super(message);
        this.metadata = metadata;
    }
}
