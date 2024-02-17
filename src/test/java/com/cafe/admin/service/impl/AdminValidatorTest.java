package com.cafe.admin.service.impl;

import com.cafe.admin.service.vo.SignUpForm;
import com.cafe.admin.service.vo.SignUpFormFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class AdminValidatorTest {

    @InjectMocks
    AdminValidator adminValidator;
    @Mock
    AdminDuplicationValidator adminDuplicationValidator;

    @Test
    void 회원가입_정보를_검증할_수_있다() {
        // given
        SignUpForm signUpForm = SignUpFormFixture.STANDARD.newInstance();

        // when
        adminValidator.validate(signUpForm);

        // then
        then(adminDuplicationValidator).should(times(1)).validatePhoneNumber(signUpForm.phoneNumber());
    }

}
