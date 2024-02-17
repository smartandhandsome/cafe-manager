package com.cafe.admin.service.impl;

import com.cafe.admin.service.vo.EncryptedSignUpForm;
import com.cafe.admin.service.vo.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensitiveDataEncryptor {
    private final PasswordEncoder passwordEncoder;

    public EncryptedSignUpForm encrypt(SignUpForm signUpForm) {
        String encodedPassword = passwordEncoder.encode(signUpForm.password());
        return new EncryptedSignUpForm(signUpForm.phoneNumber(), encodedPassword);
    }

}
