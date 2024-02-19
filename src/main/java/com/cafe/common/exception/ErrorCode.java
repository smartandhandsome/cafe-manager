package com.cafe.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ADMIN
    FAILED_LOGIN(404, "휴대폰번호 또는 비밀번호를 확인해주세요."),
    DUPLICATED_PHONE_NUMBER(409, "중복된 휴대폰 번호입니다."),

    // AUTH
    ILLEGAL_TOKEN(401, "잘못된 토큰입니다."),
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    FORBIDDEN(403, "접근 권한이 없습니다."),

    // PRODUCT
    DUPLICATED_CATEGORY_NAME(409, "중복된 카테고리 이름입니다."),
    DUPLICATED_BARCODE(409, "중복된 바코드입니다."),
    DUPLICATED_SIZE_NAME(409, "중복된 사이즈 이름입니다."),

    // COMMON
    INVALID_INPUT(400, "잘못된 입력 값입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 에러입니다.");

    private final int code;
    private final String message;
}
