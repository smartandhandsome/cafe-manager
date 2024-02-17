package com.cafe.service.admin.vo;

public enum EncryptedSignUpFormFixture {
    STANDARD(Constants.PHONE_NUMBER, Constants.ENCODED_PASSWORD);

    private final String phoneNumber;
    private final String encodedPassword;

    EncryptedSignUpFormFixture(String phoneNumber, String encodedPassword) {
        this.phoneNumber = phoneNumber;
        this.encodedPassword = encodedPassword;
    }

    public EncryptedSignUpForm newInstance() {
        return new EncryptedSignUpForm(phoneNumber, encodedPassword);
    }

    private static final class Constants {
        private static final String PHONE_NUMBER = "phoneNumber";
        private static final String ENCODED_PASSWORD = "encodedPassword";
    }

}
