package com.cafe.presentation.auth;

import com.cafe.common.model.MyCafeResponse;
import com.cafe.config.ControllerTestConfig;
import com.cafe.common.exception.ErrorCode;
import com.cafe.presentation.auth.request.LoginRequest;
import com.cafe.presentation.auth.request.LoginRequestFixture;
import com.cafe.presentation.auth.response.LoginResponse;
import com.cafe.presentation.auth.response.LoginResponseFixture;
import com.cafe.service.auth.AuthService;
import com.cafe.service.auth.vo.AuthToken;
import com.cafe.service.auth.vo.AuthTokenFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(ControllerTestConfig.class)
@WebMvcTest(AuthController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @MockBean
    private AuthService authService;
    @Autowired
    private AuthController authController;

    @Nested
    class 로그인_요청을_할_수_있다 {

        private static Stream<Arguments> loginRequests() {
            return Stream.of(
                    Arguments.of(LoginRequestFixture.NULL_PHONE_NUMBER.newInstance(), "휴대폰 번호 null"),
                    Arguments.of(LoginRequestFixture.NULL_PASSWORD.newInstance(), "패스워드 null"),
                    Arguments.of(LoginRequestFixture.NULL_PHONE_NUMBER_NULL_PASSWORD.newInstance(), "휴대폰 번호, 패스워드 null"),
                    Arguments.of(LoginRequestFixture.BLANK_PHONE_NUMBER.newInstance(), "휴대폰 번호 blank"),
                    Arguments.of(LoginRequestFixture.BLANK_PASSWORD.newInstance(), "패스워드 blank"),
                    Arguments.of(LoginRequestFixture.BLANK_PHONE_NUMBER_BLANK_PASSWORD.newInstance(), "휴대폰 번호, 패스워드 blank"),
                    Arguments.of(LoginRequestFixture.WRONG_PHONE_NUMBER.newInstance(), "옳지 않은 휴대폰 번호 Pattern")
            );
        }

        @Test
        void 성공() throws Exception {
            // given
            LoginRequest loginRequest = LoginRequestFixture.STANDARD.newInstance();
            AuthToken authToken = AuthTokenFixture.STANDARD.newInstance();
            LoginResponse loginResponse = LoginResponseFixture.STANDARD.newInstance(authToken.value());
            MyCafeResponse<LoginResponse> myCafeResponse = MyCafeResponse.success(loginResponse);

            String requestBody = om.writeValueAsString(loginRequest);
            String responseBody = om.writeValueAsString(myCafeResponse);

            given(authService.login(loginRequest.toLoginForm())).willReturn(authToken);

            // when
            mvc.perform(
                            post("/v1/auth/login")
                                    .contentType(APPLICATION_JSON)
                                    .content(requestBody)
                    ) // then
                    .andExpect(status().isOk())
                    .andExpect(content().json(responseBody));
        }

        @ParameterizedTest(name = "{1}")
        @MethodSource("loginRequests")
        void 실패(LoginRequest loginRequest, String message) throws Exception {
            // given
            AuthToken authToken = AuthTokenFixture.STANDARD.newInstance();
            MyCafeResponse<Void> myCafeResponse = MyCafeResponse.fail(ErrorCode.INVALID_INPUT);

            String requestBody = om.writeValueAsString(loginRequest);
            String responseBody = om.writeValueAsString(myCafeResponse);

            given(authService.login(loginRequest.toLoginForm())).willReturn(authToken);

            // when
            mvc.perform(
                            post("/v1/auth/login")
                                    .contentType(APPLICATION_JSON)
                                    .content(requestBody)
                    ) // then
                    .andExpect(status().isBadRequest())
                    .andExpect(content().json(responseBody));
        }
    }

}
