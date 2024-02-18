package com.cafe.admin.presentation.request;

import com.cafe.admin.presentation.request.LoginRequest;

public enum LoginRequestFixture {
    STANDARD(Constants.PHONE_NUMBER, Constants.PASSWORD),
    NULL_PHONE_NUMBER(null, Constants.PHONE_NUMBER),
    NULL_PASSWORD(Constants.PHONE_NUMBER, null),
    NULL_PHONE_NUMBER_NULL_PASSWORD(null, null),
    BLANK_PHONE_NUMBER(Constants.BLANK, Constants.PASSWORD),
    BLANK_PASSWORD(Constants.PHONE_NUMBER, Constants.BLANK),
    BLANK_PHONE_NUMBER_BLANK_PASSWORD(Constants.BLANK, Constants.BLANK),
    WRONG_PHONE_NUMBER(Constants.WRONG_PHONE_NUMBER, Constants.PASSWORD),
    ;

    private final String phoneNumber;
    private final String password;

    LoginRequestFixture(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public LoginRequest newInstance() {
        return new LoginRequest(phoneNumber, password);
    }

    private static final class Constants {
        private static final String PHONE_NUMBER = "010-1234-5678";
        private static final String PASSWORD = "password";
        private static final String BLANK = "";
        private static final String WRONG_PHONE_NUMBER = "0101-1111-112121";
    }

}
