package com.cafe.presentation.admin;

import com.cafe.presentation.admin.request.SignUpRequest;
import com.cafe.presentation.admin.request.SignUpRequestFixture;
import com.cafe.config.ControllerConfig;
import com.cafe.service.admin.AdminService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(ControllerConfig.class)
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
        @Test
        void 성공() throws Exception {
            // given
            SignUpRequest signUpRequest = SignUpRequestFixture.STANDARD.newInstance();
            String body = om.writeValueAsString(signUpRequest);


            // when, then
            mvc.perform(
                    post("/v1/admin")
                            .contentType(APPLICATION_JSON)
                            .content(body)
            ).andExpect(status().isOk());

            then(adminService).should().signUp(signUpRequest.toSignUpForm());
        }

        @ParameterizedTest(name = "{1}")
        @MethodSource("signUpRequests")
        void 실패(SignUpRequest signUpRequest, String message) throws Exception {
            // given
            String body = om.writeValueAsString(signUpRequest);


            // when
            mvc.perform(
                            post("/v1/admin")
                                    .contentType(APPLICATION_JSON)
                                    .content(body)
                    )
                    // then
                    .andExpect(status().isBadRequest());

            then(adminService).should(never()).signUp(signUpRequest.toSignUpForm());
        }

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
    }


}
