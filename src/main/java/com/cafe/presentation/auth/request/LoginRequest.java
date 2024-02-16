package com.cafe.presentation.auth.request;

import com.cafe.service.auth.vo.LoginForm;

public record LoginRequest(
        String phoneNumber,
        String password
) {
    public LoginForm toLoginForm() {
        return new LoginForm(phoneNumber, password);
    }
}
