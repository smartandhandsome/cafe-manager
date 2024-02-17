package com.cafe.presentation.auth.response;

public enum LoginResponseFixture {
    STANDARD,
    ;

    LoginResponseFixture() {
    }

    public static LoginResponse newInstance(String value) {
        return new LoginResponse(value);
    }

}
