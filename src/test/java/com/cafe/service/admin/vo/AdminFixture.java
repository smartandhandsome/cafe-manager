package com.cafe.service.admin.vo;

public enum AdminFixture {
    STANDARD(
            Constants.PHONE_NUMBER,
            Constants.ENCODED_PASSWORD
    );

    private final String phoneNumber;
    private final String encodedPassword;

    AdminFixture(String phoneNumber, String encodedPassword) {
        this.phoneNumber = phoneNumber;
        this.encodedPassword = encodedPassword;
    }

    public Admin newInstance() {
        return new Admin(phoneNumber, encodedPassword);
    }

    private static final class Constants {
        private static final String PHONE_NUMBER = "010-1234-5678";
        private static final String ENCODED_PASSWORD = "encodedPassword";
    }

}
