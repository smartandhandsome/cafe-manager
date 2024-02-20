package com.cafe.admin.presentation.response;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
