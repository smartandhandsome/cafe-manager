package com.cafe.service.admin;

import com.cafe.service.admin.impl.AdminCreator;
import com.cafe.service.admin.impl.AdminValidator;
import com.cafe.service.admin.impl.SensitiveDataEncryptor;
import com.cafe.service.admin.vo.EncryptedSignUpForm;
import com.cafe.service.admin.vo.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final SensitiveDataEncryptor sensitiveDataEncryptor;
    private final AdminValidator adminValidator;
    private final AdminCreator adminCreator;

    public void signUp(SignUpForm signUpForm) {
        EncryptedSignUpForm encryptedSignUpForm = sensitiveDataEncryptor.encrypt(signUpForm);
        adminValidator.validate(signUpForm);
        adminCreator.create(encryptedSignUpForm);
    }

}