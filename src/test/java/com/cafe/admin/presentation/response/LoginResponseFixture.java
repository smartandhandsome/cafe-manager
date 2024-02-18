package com.cafe.admin.presentation.response;

import com.cafe.admin.presentation.response.LoginResponse;

public enum LoginResponseFixture {
    STANDARD,
    ;

    LoginResponseFixture() {
    }

    public static LoginResponse newInstance(String value) {
        return new LoginResponse(value);
    }

}
