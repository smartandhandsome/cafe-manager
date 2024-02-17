package com.cafe.persistence.admin.entity;

import com.cafe.persistence.util.EntityIdInjector;

public enum AdminJpaEntityFixture {
    STANDARD(
            Constants.ADMIN_ID,
            Constants.PHONE_NUMBER,
            Constants.ENCODED_PASSWORD
    ),
    NOT_EXISTED(
            Constants.NOT_EXISTED_ADMIN_ID,
            Constants.NOT_EXISTED_PHONE_NUMBER,
            Constants.NOT_EXISTED_ENCODED_PASSWORD
    );

    private final Long adminId;
    private final String phoneNumber;
    private final String encodedPassword;

    AdminJpaEntityFixture(Long adminId, String phoneNumber, String encodedPassword) {
        this.adminId = adminId;
        this.phoneNumber = phoneNumber;
        this.encodedPassword = encodedPassword;
    }

    public AdminJpaEntity newInstance() {
        AdminJpaEntity entity = AdminJpaEntity.builder()
                .phoneNumber(phoneNumber)
                .encodedPassword(encodedPassword)
                .build();
        EntityIdInjector.inject(entity, "adminId", adminId);
        return entity;
    }

    private static final class Constants {
        private static final Long ADMIN_ID = 1L;
        private static final String PHONE_NUMBER = "010-1234-5678";
        private static final String ENCODED_PASSWORD = "encodedPassword";

        private static final Long NOT_EXISTED_ADMIN_ID = -1L;
        private static final String NOT_EXISTED_PHONE_NUMBER = "notExistedPhoneNumber";
        private static final String NOT_EXISTED_ENCODED_PASSWORD = "notExistedEncodedPassword";

    }

}
