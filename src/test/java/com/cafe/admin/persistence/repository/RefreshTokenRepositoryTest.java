package com.cafe.admin.persistence.repository;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class RefreshTokenRepositoryTest {

    public static final String REFRESH_TOKEN = "refreshToken";
    public static final LocalDateTime EXPIRES_AT = LocalDateTime.now().plusHours(1);
    RefreshTokenRepository repository = new RefreshTokenRepository();

    @Test
    void name() {
        // given

        // when
        repository.put();

        // then

    }

}
