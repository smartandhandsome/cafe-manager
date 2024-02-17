package com.cafe.presentation.auth.request;

import com.cafe.service.auth.vo.LoginForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.cafe.common.constants.RegexPattern.PHONE_REGEX;

@Schema(description = "로그인 요청 객체")
public record LoginRequest(
        @Schema(description = "휴대폰번호")
        @NotBlank
        @Pattern(regexp = PHONE_REGEX)
        String phoneNumber,
        @Schema(description = "비밀번호")
        @NotBlank
        String password
) {
    public LoginForm toLoginForm() {
        return new LoginForm(phoneNumber, password);
    }
}
