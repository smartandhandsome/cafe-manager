package com.cafe.admin.service.vo;

public record AuthToken(
        String accessToken,
        String refreshToken
) {
}
