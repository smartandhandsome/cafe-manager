package com.cafe.admin.service;

import com.cafe.admin.service.impl.AdminCreator;
import com.cafe.admin.service.impl.AdminValidator;
import com.cafe.admin.service.impl.SensitiveDataEncryptor;
import com.cafe.admin.service.vo.EncryptedSignUpForm;
import com.cafe.admin.service.vo.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final SensitiveDataEncryptor sensitiveDataEncryptor;
    private final AdminValidator adminValidator;
    private final AdminCreator adminCreator;

    public void signUp(SignUpForm signUpForm) {
        adminValidator.validate(signUpForm);
        EncryptedSignUpForm encryptedSignUpForm = sensitiveDataEncryptor.encrypt(signUpForm);
        adminCreator.create(encryptedSignUpForm);
    }

}
