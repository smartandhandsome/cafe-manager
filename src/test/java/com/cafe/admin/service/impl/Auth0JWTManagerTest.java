package com.cafe.admin.service.impl;

import com.auth0.jwt.JWT;
import com.cafe.admin.persistence.dto.AdminIdRefreshTokenDto;
import com.cafe.admin.service.vo.AuthToken;
import com.cafe.admin.service.vo.AuthTokenFixture;
import com.cafe.common.exception.ExpiredTokenException;
import com.cafe.common.exception.IllegalTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Auth0JWTManagerTest {

    Auth0JWTManager auth0JWTManager;
    @Mock
    RefreshTokenReader refreshTokenReader;
    @Mock
    RefreshTokenDeleter refreshTokenDeleter;
    @Mock
    RefreshTokenUpdater refreshTokenUpdater;

    String secretKey = "secret";
    long accessTokenExpirationTime = 1000;
    long refreshTokenExpirationTime = 10000;

    Long adminId = 1L;
    String phoneNumber = "010-1234-5678";

    @BeforeEach
    void setUp() {
        auth0JWTManager = new Auth0JWTManager(
                refreshTokenReader,
                refreshTokenDeleter,
                refreshTokenUpdater,
                secretKey,
                accessTokenExpirationTime,
                refreshTokenExpirationTime
        );
    }

    @Test
    void 토큰을_생성할_수_있다() {
        // given

        // when
        AuthToken token = auth0JWTManager.generate(adminId);

        // then
        assertThat(
                JWT.decode(token.accessToken())
                        .getClaim(Auth0JWTManager.ADMIN_ID)
                        .asLong()
        ).isEqualTo(adminId);
        assertThat(
                JWT.decode(token.refreshToken())
                        .getClaim(Auth0JWTManager.ADMIN_ID)
                        .asLong()
        ).isEqualTo(adminId);
    }

    @Nested
    class 토큰의_정보를_추출할_수_있다 {

        @Test
        void 정상_토큰() {
            // given
            AuthToken token = auth0JWTManager.generate(adminId);

            // when
            Long extractAdminId = auth0JWTManager.extractAdminId(token.accessToken());

            // then
            assertThat(extractAdminId)
                    .isEqualTo(adminId);
        }


        @Test
        void 만료된_토근() {
            // given
            long tokenExpirationTime = -1L;
            Auth0JWTManager expiredTokenManager = new Auth0JWTManager(
                    refreshTokenReader,
                    refreshTokenDeleter,
                    refreshTokenUpdater,
                    secretKey,
                    tokenExpirationTime,
                    tokenExpirationTime
            );

            AuthToken authToken = expiredTokenManager.generate(adminId);

            // when
            // then
            String tokenValue = authToken.accessToken();
            assertThatThrownBy(() -> expiredTokenManager.extractAdminId(tokenValue))
                    .isExactlyInstanceOf(ExpiredTokenException.class);
        }

        @Test
        void 올바르지_않은_토큰() {
            // given
            AuthToken authToken = AuthTokenFixture.ILLEGAL.newInstance();

            // when

            // then
            String tokenValue = authToken.accessToken();
            assertThatThrownBy(() -> auth0JWTManager.extractAdminId(tokenValue))
                    .isExactlyInstanceOf(IllegalTokenException.class);
        }

    }


    @Nested
    class 저장소의_만료된_토큰을_모두_삭제할_수_있다 {

        @Test
        void 만료된_토큰_없음() {
            // given
            Set<AdminIdRefreshTokenDto> dtoSet = new HashSet<>();
            for (int i = 1; i <= 3; i++) {
                AuthToken generate = auth0JWTManager.generate((long) i);
                dtoSet.add(new AdminIdRefreshTokenDto((long) i, generate.refreshToken()));
            }
            given(refreshTokenReader.readAll()).willReturn(dtoSet);

            // when
            auth0JWTManager.deleteAllExpired();

            // then
            then(refreshTokenDeleter).should(never()).delete(anyLong());
        }

        @Test
        void 만료된_토큰_있음() {
            // given
            long tokenExpirationTime = -1L;
            Auth0JWTManager expiredTokenManager = new Auth0JWTManager(
                    refreshTokenReader,
                    refreshTokenDeleter,
                    refreshTokenUpdater,
                    secretKey,
                    tokenExpirationTime,
                    tokenExpirationTime
            );

            Set<AdminIdRefreshTokenDto> dtoSet = new HashSet<>();
            int count = 3;
            for (int i = 1; i <= count; i++) {
                AuthToken generate = expiredTokenManager.generate((long) i);
                dtoSet.add(new AdminIdRefreshTokenDto((long) i, generate.refreshToken()));
            }
            given(refreshTokenReader.readAll()).willReturn(dtoSet);

            // when
            expiredTokenManager.deleteAllExpired();

            // then
            then(refreshTokenDeleter).should(times(count)).delete(anyLong());
        }

    }

}
