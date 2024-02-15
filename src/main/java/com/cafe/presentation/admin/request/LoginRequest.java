package com.cafe.presentation.admin.request;

import com.cafe.service.admin.vo.LoginForm;

public record LoginRequest(
        String phoneNumber,
        String password
) {
    public LoginForm toLoginForm() {
        return new LoginForm(phoneNumber, password);
    }
}
