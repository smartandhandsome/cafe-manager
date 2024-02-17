package com.cafe.common.model;

import com.cafe.common.exception.ErrorCode;

public record MyCafeResponse<T>(
        ResponseMetadata meta,
        T data
) {

    public static MyCafeResponse<Void> success() {
        return new MyCafeResponse<>(
                ResponseMetadata.success(),
                null
        );
    }

    public static <E> MyCafeResponse<E> success(E data) {
        return new MyCafeResponse<>(
                ResponseMetadata.success(),
                data
        );
    }

    public static MyCafeResponse<Void> fail(ErrorCode errorCode) {
        return new MyCafeResponse<>(
                ResponseMetadata.from(errorCode),
                null
        );
    }

}
