package com.cafe.admin.service;

import com.cafe.admin.service.impl.AdminCreator;
import com.cafe.admin.service.impl.AdminDuplicationValidator;
import com.cafe.admin.service.impl.SensitiveDataEncryptor;
import com.cafe.admin.service.vo.EncryptedSignUpForm;
import com.cafe.admin.service.vo.EncryptedSignUpFormFixture;
import com.cafe.admin.service.vo.SignUpForm;
import com.cafe.admin.service.vo.SignUpFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AdminServiceTest {

    @InjectMocks
    AdminService adminService;
    @Mock
    SensitiveDataEncryptor sensitiveDataEncryptor;
    @Mock
    AdminCreator adminCreator;
    @Mock
    AdminDuplicationValidator adminDuplicationValidator;

    @Test
    void 회원가입을_할_수_있다() {
        // given
        SignUpForm signUpForm = SignUpFormFixture.STANDARD.newInstance();
        EncryptedSignUpForm encryptedSignUpForm = EncryptedSignUpFormFixture.STANDARD.newInstance();

        given(sensitiveDataEncryptor.encrypt(signUpForm)).willReturn(encryptedSignUpForm);

        // when
        adminService.signUp(signUpForm);

        // then
        then(adminDuplicationValidator).should(times(1)).validate(signUpForm.phoneNumber());
        then(sensitiveDataEncryptor).should(times(1)).encrypt(signUpForm);
        then(adminCreator).should(times(1)).create(encryptedSignUpForm);
    }

}
