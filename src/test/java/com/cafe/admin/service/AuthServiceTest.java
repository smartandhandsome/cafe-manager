package com.cafe.admin.service;

import com.cafe.admin.service.impl.AdminReader;
import com.cafe.admin.service.impl.AuthTokenGenerator;
import com.cafe.admin.service.impl.AuthValidator;
import com.cafe.admin.service.impl.RefreshTokenDeleter;
import com.cafe.admin.service.vo.AuthToken;
import com.cafe.admin.service.vo.AuthTokenFixture;
import com.cafe.admin.service.vo.LoginForm;
import com.cafe.admin.service.vo.LoginFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;
    @Mock
    AdminReader adminReader;
    @Mock
    AuthValidator authValidator;
    @Mock
    AuthTokenGenerator tokenGenerator;
    @Mock
    RefreshTokenDeleter refreshTokenDeleter;

    @Test
    void 로그인할_수_있다() {
        // given
        Long adminId = 1L;
        LoginForm loginForm = LoginFormFixture.STANDARD.newInstance();
        AuthToken authToken = AuthTokenFixture.STANDARD.newInstance();

        given(tokenGenerator.generate(adminId)).willReturn(authToken);
        given(adminReader.readAdminIdByPhoneNumber(loginForm.phoneNumber())).willReturn(adminId);

        // when
        AuthToken generatedToken = authService.login(loginForm);

        // then
        then(authValidator).should(times(1)).validate(loginForm);
        assertThat(generatedToken)
                .usingRecursiveComparison()
                .isEqualTo(authToken);
    }

    @Test
    void 토큰_재발행을_할_수_있다() {
        // given
        Long adminId = 1L;
        AuthToken authToken = AuthTokenFixture.STANDARD.newInstance();

        given(tokenGenerator.generate(adminId)).willReturn(authToken);

        // when
        AuthToken reissuedAuthToken = authService.reissue(adminId);

        // then
        then(authValidator).should(times(1)).validateRefreshToken(adminId);
        assertThat(reissuedAuthToken).usingRecursiveComparison()
                .isEqualTo(authToken);
    }

    @Test
    void 로그아웃을_할_수_있다() {
        // given
        Long adminId = 1L;

        // when
        authService.logout(adminId);

        // then
        then(refreshTokenDeleter).should(times(1)).delete(adminId);
    }

}
