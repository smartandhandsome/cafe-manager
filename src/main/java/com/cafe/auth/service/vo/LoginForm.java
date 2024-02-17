package com.cafe.auth.service.vo;

public record LoginForm(
        String phoneNumber,
        String password
) {
}
