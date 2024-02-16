package com.cafe.persistence.admin.entity;

import com.cafe.persistence.util.EntityIdInjector;

public enum AdminJpaEntityFixture {
    STANDARD(1L, "010-1234-5678", "encodedPassword");

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

}
