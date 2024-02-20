package com.cafe.admin.persistence.dto;

public record AdminIdRefreshTokenDto(
        Long adminId,
        String refreshToken
) {
}
