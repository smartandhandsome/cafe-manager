package com.cafe.admin.service.impl;

import com.cafe.admin.service.vo.Admin;
import com.cafe.admin.service.vo.AdminFixture;
import com.cafe.admin.service.vo.LoginForm;
import com.cafe.admin.service.vo.LoginFormFixture;
import com.cafe.common.exception.LoginFailException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthValidatorTest {

    @InjectMocks
    AuthValidator authValidator;
    @Mock
    AdminReader adminReader;
    @Mock
    PasswordValidator passwordValidator;

    @Nested
    class 로그인_정보를_검증할_수_있다 {

        @Test
        void 정상_로그인_정보() {
            // given
            LoginForm loginForm = LoginFormFixture.STANDARD.newInstance();
            Admin admin = AdminFixture.STANDARD.newInstance();

            given(adminReader.readByPhoneNumber(loginForm.phoneNumber()))
                    .willReturn(admin);
            willDoNothing()
                    .given(passwordValidator)
                    .validate(loginForm.password(), admin.encodedPassword());

            // when, then
            assertThatCode(() -> authValidator.validate(loginForm))
                    .doesNotThrowAnyException();
        }

        @Test
        void 존재하지_않는_휴대폰_번호() {
            // given
            LoginForm loginForm = LoginFormFixture.STANDARD.newInstance();
            Admin admin = AdminFixture.STANDARD.newInstance();

            given(adminReader.readByPhoneNumber(loginForm.phoneNumber()))
                    .willThrow(EntityNotFoundException.class);

            // when, then
            assertThatThrownBy(() -> authValidator.validate(loginForm))
                    .isExactlyInstanceOf(LoginFailException.class);
        }

        @Test
        void 비밀번호_불일치() {
            // given
            LoginForm loginForm = LoginFormFixture.STANDARD.newInstance();
            Admin admin = AdminFixture.STANDARD.newInstance();

            given(adminReader.readByPhoneNumber(loginForm.phoneNumber()))
                    .willReturn(admin);
            willThrow(IllegalArgumentException.class)
                    .given(passwordValidator)
                    .validate(loginForm.password(), admin.encodedPassword());

            // when, then
            assertThatThrownBy(() -> authValidator.validate(loginForm))
                    .isExactlyInstanceOf(LoginFailException.class);
        }
    }

}
