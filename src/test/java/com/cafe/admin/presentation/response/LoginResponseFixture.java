package com.cafe.admin.presentation.response;

public enum LoginResponseFixture {
    STANDARD,
    ;

    LoginResponseFixture() {
    }

    public static LoginResponse newInstance(String value) {
        return new LoginResponse(value);
    }

}
