package com.cafe.admin.service.impl;

import com.cafe.admin.service.vo.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminValidator {

    private final AdminDuplicationValidator adminDuplicationValidator;

    public void validate(SignUpForm signUpForm) {
        adminDuplicationValidator.validatePhoneNumber(signUpForm.phoneNumber());
    }

}
