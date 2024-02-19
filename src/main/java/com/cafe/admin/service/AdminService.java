package com.cafe.admin.service;

import com.cafe.admin.service.impl.AdminCreator;
import com.cafe.admin.service.impl.AdminDuplicationValidator;
import com.cafe.admin.service.impl.SensitiveDataEncryptor;
import com.cafe.admin.service.vo.EncryptedSignUpForm;
import com.cafe.admin.service.vo.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final SensitiveDataEncryptor sensitiveDataEncryptor;
    private final AdminDuplicationValidator adminDuplicationValidator;
    private final AdminCreator adminCreator;

    @Transactional
    public void signUp(SignUpForm signUpForm) {
        adminDuplicationValidator.validate(signUpForm.phoneNumber());
        EncryptedSignUpForm encryptedSignUpForm = sensitiveDataEncryptor.encrypt(signUpForm);
        adminCreator.create(encryptedSignUpForm);
    }

}
