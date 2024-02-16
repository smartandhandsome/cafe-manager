package com.cafe.common;

public record MyCafeResponse<T>(
        ResponseMetadata meta,
        T data
) {

    public static MyCafeResponse<Void> success() {
        return new MyCafeResponse<>(ResponseMetadata.SUCCESS, null);
    }

    public static <E> MyCafeResponse<E> success(E data) {
        return new MyCafeResponse<>(ResponseMetadata.SUCCESS, data);
    }

}
