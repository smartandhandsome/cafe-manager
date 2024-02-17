package com.cafe.auth.service.impl;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PasswordValidatorTest {

    String password = "password";
    String encodedPassword = "encodedPassword";
    String wrongPassword = "wrongPassword";

    @InjectMocks
    PasswordValidator passwordValidator;
    @Mock
    PasswordEncoder passwordEncoder;

    @Nested
    class 비밀번호가_일치하는지_검증할_수_있다 {

        @Test
        void 일치() {
            // given
            given(passwordEncoder.matches(password, encodedPassword)).willReturn(true);

            // when, then
            assertThatCode(
                    () -> passwordValidator.validate(password, encodedPassword)
            ).doesNotThrowAnyException();
        }

        @Test
        void 불일치() {
            // given
            given(passwordEncoder.matches(password, encodedPassword)).willReturn(false);

            // when, then
            assertThatThrownBy(
                    () -> passwordValidator.validate(password, encodedPassword)
            ).isExactlyInstanceOf(IllegalArgumentException.class);
        }

    }

}
