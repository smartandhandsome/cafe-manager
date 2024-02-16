package com.cafe.presentation.admin.request;

public enum SignUpRequestFixture {
    STANDARD(Constants.phoneNumber, Constants.password),
    NULL_PHONE_NUMBER(null, Constants.phoneNumber),
    NULL_PASSWORD(Constants.phoneNumber, null),
    NULL_PHONE_NUMBER_NULL_PASSWORD(null, null),
    BLANK_PHONE_NUMBER(Constants.blank, Constants.password),
    BLANK_PASSWORD(Constants.phoneNumber, Constants.blank),
    BLANK_PHONE_NUMBER_BLANK_PASSWORD(Constants.blank, Constants.blank),
    WRONG_PHONE_NUMBER(Constants.wrongPhoneNumber, Constants.password),
    ;

    private final String phoneNumber;
    private final String password;

    SignUpRequestFixture(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public SignUpRequest newInstance() {
        return new SignUpRequest(phoneNumber, password);
    }

    private static class Constants {
        private static final String phoneNumber = "010-1234-5678";
        private static final String password = "password";
        private static final String blank = "";
        private static final String wrongPhoneNumber = "0101-1111-112121";
    }

}
