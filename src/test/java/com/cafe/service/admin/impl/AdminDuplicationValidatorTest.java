package com.cafe.service.admin.impl;

import com.cafe.common.exception.DuplicatedResourceException;
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

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AdminDuplicationValidatorTest {

    @InjectMocks
    AdminDuplicationValidator adminDuplicationValidator;
    @Mock
    AdminReader adminReader;

    @Nested
    class 휴대폰_번호_중복을_검증할_수_있다 {

        @Test
        void 휴대폰_번호_중복_되지_않음() {
            // given
            String phoneNumber = "010-1234-5678";

            given(adminReader.hasDuplicatedPhoneNumber(phoneNumber)).willReturn(false);

            // when, then
            assertThatCode(() -> adminDuplicationValidator.validatePhoneNumber(phoneNumber))
                    .doesNotThrowAnyException();
        }

        @Test
        void 휴대폰_번호_중복() {
            // given
            String phoneNumber = "010-1234-5678";

            given(adminReader.hasDuplicatedPhoneNumber(phoneNumber)).willReturn(true);

            // when, then
            assertThatThrownBy(() -> adminDuplicationValidator.validatePhoneNumber(phoneNumber))
                    .isExactlyInstanceOf(DuplicatedResourceException.class);
        }

    }

}
