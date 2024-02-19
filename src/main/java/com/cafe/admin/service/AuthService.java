package com.cafe.admin.service;

import com.cafe.admin.service.impl.AuthTokenGenerator;
import com.cafe.admin.service.impl.AuthValidator;
import com.cafe.admin.service.vo.AuthToken;
import com.cafe.admin.service.vo.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthValidator authValidator;
    private final AuthTokenGenerator tokenGenerator;

    @Transactional(readOnly = true)
    public AuthToken login(LoginForm loginForm) {
        authValidator.validate(loginForm);
        return tokenGenerator.generate(loginForm.phoneNumber());
    }

}
