package com.cafe.service.admin.impl;

import com.cafe.service.admin.vo.SignUpForm;
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
