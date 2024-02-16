package com.cafe.common.exception;

import com.cafe.common.ResponseMetadata;

public class DuplicatedResourceException extends CafeException {

    public DuplicatedResourceException(ResponseMetadata metadata, String message) {
        super(metadata, message);
    }
}
