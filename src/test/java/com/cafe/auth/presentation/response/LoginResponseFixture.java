package com.cafe.auth.presentation.response;

public enum LoginResponseFixture {
    STANDARD,
    ;

    LoginResponseFixture() {
    }

    public static LoginResponse newInstance(String value) {
        return new LoginResponse(value);
    }

}
