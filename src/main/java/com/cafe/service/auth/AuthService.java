package com.cafe.service.auth;

import com.cafe.service.auth.impl.AuthTokenGenerator;
import com.cafe.service.auth.impl.AuthValidator;
import com.cafe.service.auth.vo.AuthToken;
import com.cafe.service.auth.vo.LoginForm;
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
