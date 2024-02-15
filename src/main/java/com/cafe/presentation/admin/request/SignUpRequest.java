package com.cafe.presentation.admin.request;

import com.cafe.service.admin.vo.SignUpForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import static com.cafe.constants.RegexPattern.PHONE_REGEX;

@Schema(description = "회원가입 요청 객체")
public record SignUpRequest(
        @Schema(description = "휴대폰번호")
        @Pattern(regexp = PHONE_REGEX)
        String phoneNumber,
        @Schema(description = "비밀번호")
        String password
) {

    public SignUpForm toSignUpForm() {
        return new SignUpForm(phoneNumber, password);
    }

}
