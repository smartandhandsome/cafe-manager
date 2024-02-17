package com.cafe.auth.service;

import com.cafe.auth.service.impl.AuthTokenGenerator;
import com.cafe.auth.service.impl.AuthValidator;
import com.cafe.auth.service.vo.AuthToken;
import com.cafe.auth.service.vo.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthValidator authValidator;
    private final AuthTokenGenerator tokenGenerator;

    public AuthToken login(LoginForm loginForm) {
        authValidator.validate(loginForm);
        return tokenGenerator.generate(loginForm.phoneNumber());
    }

}
