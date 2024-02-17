package com.cafe.admin.presentation;

import com.cafe.admin.presentation.request.SignUpRequest;
import com.cafe.admin.presentation.request.SignUpRequestFixture;
import com.cafe.admin.service.AdminService;
import com.cafe.common.config.ControllerTestConfig;
import com.cafe.common.exception.ErrorCode;
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
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(ControllerTestConfig.class)
@WebMvcTest(AdminController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @MockBean
    private AdminService adminService;
    @Autowired
    private AdminController controller;

    @Nested
    class 회원가입_요청을_할_수_있다 {
        static Stream<Arguments> signUpRequests() {
            return Stream.of(
                    Arguments.of(SignUpRequestFixture.NULL_PHONE_NUMBER.newInstance(), "휴대폰 번호 null"),
                    Arguments.of(SignUpRequestFixture.NULL_PASSWORD.newInstance(), "패스워드 null"),
                    Arguments.of(SignUpRequestFixture.NULL_PHONE_NUMBER_NULL_PASSWORD.newInstance(), "휴대폰 번호, 패스워드 null"),
                    Arguments.of(SignUpRequestFixture.BLANK_PHONE_NUMBER.newInstance(), "휴대폰 번호 blank"),
                    Arguments.of(SignUpRequestFixture.BLANK_PASSWORD.newInstance(), "패스워드 blank"),
                    Arguments.of(SignUpRequestFixture.BLANK_PHONE_NUMBER_BLANK_PASSWORD.newInstance(), "휴대폰 번호, 패스워드 blank"),
                    Arguments.of(SignUpRequestFixture.WRONG_PHONE_NUMBER.newInstance(), "옳지 않은 휴대폰 번호 Pattern")
            );
        }

        @Test
        void 성공() throws Exception {
            // given
            SignUpRequest signUpRequest = SignUpRequestFixture.STANDARD.newInstance();
            MyCafeResponse<Void> successResponse = MyCafeResponse.success();

            String requestBody = om.writeValueAsString(signUpRequest);
            String responseBody = om.writeValueAsString(successResponse);

            // when
            mvc.perform(
                            post("/v1/admin")
                                    .contentType(APPLICATION_JSON)
                                    .content(requestBody)
                    )
                    // then
                    .andExpect(status().isOk())
                    .andExpect(content().json(responseBody));

            then(adminService).should().signUp(signUpRequest.toSignUpForm());
        }

        @ParameterizedTest(name = "{1}")
        @MethodSource("signUpRequests")
        void 잘못된_입력값(SignUpRequest signUpRequest, String message) throws Exception {
            // given
            MyCafeResponse<Void> failResponse = MyCafeResponse.fail(ErrorCode.INVALID_INPUT);

            String requestBody = om.writeValueAsString(signUpRequest);
            String responseBody = om.writeValueAsString(failResponse);

            // when
            mvc.perform(
                            post("/v1/admin")
                                    .contentType(APPLICATION_JSON)
                                    .content(requestBody)
                    )
                    // then
                    .andExpect(status().isBadRequest())
                    .andExpect(content().json(responseBody));
            then(adminService).should(never()).signUp(signUpRequest.toSignUpForm());
        }
    }


}
