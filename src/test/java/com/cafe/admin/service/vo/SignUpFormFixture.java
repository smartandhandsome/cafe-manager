package com.cafe.admin.service.vo;

public enum SignUpFormFixture {
    STANDARD(Constants.phoneNumber, Constants.password);

    private final String phoneNumber;
    private final String password;

    SignUpFormFixture(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public SignUpForm newInstance() {
        return new SignUpForm(phoneNumber, password);
    }

    private static final class Constants {
        private static final String phoneNumber = "010-1234-5678";
        private static final String password = "password";
    }

}
