package com.cafe.presentation.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

@Getter
@JsonFormat(shape = OBJECT)
@RequiredArgsConstructor
public enum ResponseMetadata {
    SUCCESS(200, "ok"),
    ;

    private final int code;
    private final String message;
}
