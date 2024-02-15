package com.cafe.presentation.common;

import static com.cafe.presentation.common.ResponseMetadata.SUCCESS;

public record MyCafeResponse<T>(
        ResponseMetadata meta,
        T data
) {

    public static MyCafeResponse<Void> success() {
        return new MyCafeResponse<>(SUCCESS, null);
    }

    public static <E> MyCafeResponse<E> success(E data) {
        return new MyCafeResponse<>(SUCCESS, data);
    }

}
