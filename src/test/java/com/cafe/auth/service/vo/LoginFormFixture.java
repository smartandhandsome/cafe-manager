package com.cafe.auth.service.vo;

public enum LoginFormFixture {
    STANDARD(
            Constants.phoneNumber,
            Constants.password
    );

    private final String phoneNumber;
    private final String password;

    LoginFormFixture(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public LoginForm newInstance() {
        return new LoginForm(phoneNumber, password);
    }

    private static final class Constants {
        private final static String phoneNumber = "010-1234-5678";
        private final static String password = "password";
    }

}
