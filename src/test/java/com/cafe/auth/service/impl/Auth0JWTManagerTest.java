package com.cafe.auth.service.impl;

import com.auth0.jwt.JWT;
import com.cafe.admin.service.impl.AdminReader;
import com.cafe.admin.service.impl.Auth0JWTManager;
import com.cafe.auth.service.vo.AuthToken;
import com.cafe.auth.service.vo.AuthTokenFixture;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Auth0JWTManagerTest {

    Auth0JWTManager auth0JWTManager;
    @Mock
    AdminReader adminReader;

    String secretKey = "secret";
    long tokenExpirationTime = 1000;

    Long adminId = 1L;
    String phoneNumber = "010-1234-5678";

    @BeforeEach
    void setUp() {
        auth0JWTManager = new Auth0JWTManager(adminReader, secretKey, tokenExpirationTime);
    }

    @Test
    void 토큰을_생성할_수_있다() {
        // given
        given(adminReader.readAdminIdByPhoneNumber(phoneNumber)).willReturn(adminId);

        // when
        AuthToken token = auth0JWTManager.generate(phoneNumber);

        // then
        assertThat(
                JWT.decode(token.value())
                        .getClaim(Auth0JWTManager.ADMIN_ID)
                        .asLong()
        ).isEqualTo(adminId);
    }

    @Nested
    class 토큰의_정보를_추출할_수_있다 {

        @Test
        void 정상_토큰() {
            // given
            AuthToken token = getAuthToken(auth0JWTManager);

            // when
            Long extractAdminId = auth0JWTManager.extractAdminId(token.value());

            // then
            assertThat(extractAdminId)
                    .isEqualTo(adminId);
        }


        @Test
        void 만료된_토근() {
            // given
            long tokenExpirationTime = -1L;
            Auth0JWTManager expiredTokenManager = new Auth0JWTManager(adminReader, secretKey, tokenExpirationTime);

            AuthToken authToken = getAuthToken(expiredTokenManager);

            // when
            // then
            String tokenValue = authToken.value();
            assertThatThrownBy(() -> expiredTokenManager.extractAdminId(tokenValue))
                    .isExactlyInstanceOf(ExpiredTokenException.class);
        }

        @Test
        void 올바르지_않은_토큰() {
            // given
            AuthToken authToken = AuthTokenFixture.ILLEGAL.newInstance();

            // when

            // then
            String tokenValue = authToken.value();
            assertThatThrownBy(() -> auth0JWTManager.extractAdminId(tokenValue))
                    .isExactlyInstanceOf(IllegalTokenException.class);
        }

        private AuthToken getAuthToken(Auth0JWTManager auth0JWTManager) {
            given(adminReader.readAdminIdByPhoneNumber(phoneNumber)).willReturn(adminId);
            return auth0JWTManager.generate(phoneNumber);
        }
    }

}
