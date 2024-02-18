package com.cafe.auth.service;

import com.cafe.auth.service.impl.AuthTokenGenerator;
import com.cafe.auth.service.impl.AuthValidator;
import com.cafe.auth.service.vo.AuthToken;
import com.cafe.auth.service.vo.AuthTokenFixture;
import com.cafe.auth.service.vo.LoginForm;
import com.cafe.auth.service.vo.LoginFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;
    @Mock
    AuthValidator authValidator;
    @Mock
    AuthTokenGenerator tokenGenerator;

    @Test
    void 로그인할_수_있다() {
        // given
        LoginForm loginForm = LoginFormFixture.STANDARD.newInstance();
        AuthToken authToken = AuthTokenFixture.STANDARD.newInstance();

        given(tokenGenerator.generate(loginForm.phoneNumber())).willReturn(authToken);

        // when
        AuthToken generatedToken = authService.login(loginForm);

        // then
        then(authValidator).should(times(1)).validate(loginForm);
        assertThat(generatedToken)
                .usingRecursiveComparison()
                .isEqualTo(authToken);
    }
}
