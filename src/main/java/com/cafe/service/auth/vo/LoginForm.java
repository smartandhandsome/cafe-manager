package com.cafe.service.auth.vo;

public record LoginForm(
        String phoneNumber,
        String password
) {
}
