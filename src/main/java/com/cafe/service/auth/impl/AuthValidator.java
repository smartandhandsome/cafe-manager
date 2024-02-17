package com.cafe.service.auth.impl;

import com.cafe.exception.LoginFailException;
import com.cafe.service.admin.impl.AdminReader;
import com.cafe.service.admin.vo.Admin;
import com.cafe.service.auth.vo.LoginForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.cafe.exception.ErrorCode.FAILED_LOGIN;

@Component
@RequiredArgsConstructor
public class AuthValidator {

    private final AdminReader adminReader;
    private final PasswordValidator passwordValidator;

    public void validate(LoginForm loginForm) {
        try {
            Admin admin = adminReader.readByPhoneNumber(loginForm.phoneNumber());
            passwordValidator.validate(loginForm.password(), admin.encodedPassword());
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            throw new LoginFailException(
                    FAILED_LOGIN,
                    "로그인에 실패했습니다.",
                    e
            );
        }
    }

}
