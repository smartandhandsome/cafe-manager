package com.cafe.admin.presentation;

import com.cafe.admin.presentation.request.LoginRequest;
import com.cafe.admin.presentation.request.LoginRequestFixture;
import com.cafe.admin.presentation.response.LoginResponse;
import com.cafe.admin.presentation.response.LoginResponseFixture;
import com.cafe.admin.service.AuthService;
import com.cafe.admin.service.vo.AuthToken;
import com.cafe.admin.service.vo.AuthTokenFixture;
import com.cafe.common.exception.ErrorCode;
import com.cafe.common.model.AdminAuthorization;
import com.cafe.common.model.BaseControllerTest;
import com.cafe.common.model.MyCafeResponse;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthControllerTest extends BaseControllerTest {

    static String BASE_URI = "/v1/auth";

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
            LoginResponse loginResponse = LoginResponseFixture.newInstance(authToken.accessToken(), authToken.refreshToken());
            MyCafeResponse<LoginResponse> myCafeResponse = MyCafeResponse.success(loginResponse);

            String requestBody = om.writeValueAsString(loginRequest);
            String responseBody = om.writeValueAsString(myCafeResponse);

            given(authService.login(loginRequest.toLoginForm())).willReturn(authToken);

            // when
            mvc.perform(
                            post(BASE_URI + "/login")
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
                            post(BASE_URI + "/login")
                                    .contentType(APPLICATION_JSON)
                                    .content(requestBody)
                    ) // then
                    .andExpect(status().isBadRequest())
                    .andExpect(content().json(responseBody));
        }
    }

    @Test
    void 토큰_갱신_요청을_할_수_있다() throws Exception {
        // given
        Long adminId = 1L;

        AuthToken authToken = AuthTokenFixture.STANDARD.newInstance();
        LoginResponse loginResponse = new LoginResponse(authToken.accessToken(), authToken.refreshToken());
        MyCafeResponse<LoginResponse> response = MyCafeResponse.success(loginResponse);

        String authorizationHeader = "Bearer mytoken";

        String responseBody = om.writeValueAsString(response);

        given(authTokenExtractor.extractAdminId(anyString())).willReturn(adminId);
        given(resolver.resolveArgument(any(), any(), any(), any())).willReturn(new AdminAuthorization(adminId));
        given(authService.reissue(adminId)).willReturn(authToken);

        // when
        mvc.perform(
                        get(BASE_URI + "/reissue")
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void 로그아웃_요청을_할_수_있다() throws Exception {
        // given
        Long adminId = 1L;
        MyCafeResponse<Void> response = MyCafeResponse.success();
        String authorizationHeader = "Bearer mytoken";

        String responseBody = om.writeValueAsString(response);

        given(authTokenExtractor.extractAdminId(anyString())).willReturn(adminId);
        given(resolver.resolveArgument(any(), any(), any(), any())).willReturn(new AdminAuthorization(adminId));

        // when
        mvc.perform(
                        delete(BASE_URI + "/logout")
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(authService).should(times(1)).logout(adminId);
    }

}
