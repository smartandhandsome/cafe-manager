package com.cafe.admin.service.impl;

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
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SensitiveDataEncryptorTest {

    @InjectMocks
    SensitiveDataEncryptor sensitiveDataEncryptor;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void 회원가입_정보_중_민감정보를_암호화_할_수_있다() {
        // given
        SignUpForm signUpForm = SignUpFormFixture.STANDARD.newInstance();
        EncryptedSignUpForm expect = EncryptedSignUpFormFixture.STANDARD.newInstance();

        given(passwordEncoder.encode(signUpForm.password())).willReturn(expect.encodedPassword());

        // when
        EncryptedSignUpForm encryptedSignUpForm = sensitiveDataEncryptor.encrypt(signUpForm);

        // then
        assertThat(encryptedSignUpForm.encodedPassword()).isEqualTo(expect.encodedPassword());
    }

}
