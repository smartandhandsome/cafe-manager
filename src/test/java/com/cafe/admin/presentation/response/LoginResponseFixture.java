package com.cafe.admin.presentation.response;

public enum LoginResponseFixture {
    STANDARD,
    ;

    LoginResponseFixture() {
    }

    public static LoginResponse newInstance(
            String accessToken,
            String refreshToken
    ) {
        return new LoginResponse(accessToken, refreshToken);
    }

}
