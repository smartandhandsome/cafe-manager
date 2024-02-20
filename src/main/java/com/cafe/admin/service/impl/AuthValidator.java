package com.cafe.admin.service.impl;

import com.cafe.admin.service.vo.Admin;
import com.cafe.admin.service.vo.LoginForm;
import com.cafe.common.exception.ErrorCode;
import com.cafe.common.exception.ForbiddenException;
import com.cafe.common.exception.LoginFailException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

import static com.cafe.common.exception.ErrorCode.FAILED_LOGIN;

@Component
@RequiredArgsConstructor
public class AuthValidator {

    private final AdminReader adminReader;
    private final RefreshTokenReader refreshTokenReader;
    private final PasswordValidator passwordValidator;

    @Transactional(readOnly = true)
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

    public void validateRefreshToken(Long adminId) {
        if (!refreshTokenReader.exist(adminId)) {
            throw new ForbiddenException(
                    ErrorCode.FORBIDDEN,
                    MessageFormat.format("로그아웃한 토큰으로 재발급 시도 [admin: {0}]", adminId)
            );
        }
    }
}
