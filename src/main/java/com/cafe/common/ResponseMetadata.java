package com.cafe.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

@Getter
@JsonFormat(shape = OBJECT)
@RequiredArgsConstructor
public enum ResponseMetadata {
    SUCCESS(200, "ok"),

    DUPLICATED_PHONE_NUMBER(40000, "중복된 휴대폰 번호입니다.");
    ;

    private final int code;
    private final String message;
}
