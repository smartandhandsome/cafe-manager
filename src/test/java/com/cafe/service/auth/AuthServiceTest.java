package com.cafe.service.auth;

import com.cafe.service.auth.impl.AuthTokenGenerator;
import com.cafe.service.auth.impl.AuthValidator;
import com.cafe.service.auth.vo.AuthToken;
import com.cafe.service.auth.vo.AuthTokenFixture;
import com.cafe.service.auth.vo.LoginForm;
import com.cafe.service.auth.vo.LoginFormFixture;
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
        LoginForm loginForm = LoginFormFixture.STANDARD.getInstance();
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
