package com.cafe.service.admin.vo;

public enum EncryptedSignUpFormFixture {
    STANDARD(Constants.phoneNumber, Constants.encodedPassword);

    private final String phoneNumber;
    private final String encodedPassword;

    EncryptedSignUpFormFixture(String phoneNumber, String encodedPassword) {
        this.phoneNumber = phoneNumber;
        this.encodedPassword = encodedPassword;
    }

    public EncryptedSignUpForm newInstance() {
        return new EncryptedSignUpForm(phoneNumber, encodedPassword);
    }

    private static class Constants {
        private static final String phoneNumber = "phoneNumber";
        private static final String encodedPassword = "encodedPassword";
    }

}
