package com.cafe.admin.service;

import com.cafe.admin.service.impl.AdminReader;
import com.cafe.admin.service.impl.AuthTokenGenerator;
import com.cafe.admin.service.impl.AuthValidator;
import com.cafe.admin.service.impl.RefreshTokenDeleter;
import com.cafe.admin.service.vo.AuthToken;
import com.cafe.admin.service.vo.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminReader adminReader;
    private final AuthValidator authValidator;
    private final AuthTokenGenerator tokenGenerator;
    private final RefreshTokenDeleter refreshTokenDeleter;

    public AuthToken login(LoginForm loginForm) {
        authValidator.validate(loginForm);
        Long adminId = adminReader.readAdminIdByPhoneNumber(loginForm.phoneNumber());
        return tokenGenerator.generate(adminId);
    }

    public AuthToken reissue(Long adminId) {
        authValidator.validate(adminId);
        return tokenGenerator.generate(adminId);
    }

    public void logout(Long adminId) {
        refreshTokenDeleter.delete(adminId);
    }
}
