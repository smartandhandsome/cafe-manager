package com.cafe.presentation.auth;

import com.cafe.common.model.MyCafeResponse;
import com.cafe.presentation.auth.request.LoginRequest;
import com.cafe.presentation.auth.response.LoginResponse;
import com.cafe.service.auth.AuthService;
import com.cafe.service.auth.vo.AuthToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증/인가 API")
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(description = "관리자 로그인")
    @PostMapping("/login")
    public MyCafeResponse<LoginResponse> requestLogin(@RequestBody @Valid LoginRequest loginRequest) {
        AuthToken authToken = authService.login(loginRequest.toLoginForm());
        return MyCafeResponse.success(new LoginResponse(authToken.value()));
    }

    @Operation(description = "관리자 로그아웃")
    @PostMapping("/logout")
    public MyCafeResponse<Void> requestLogin() {
        // TODO: 2/17/24 RefreshToken 구현 시 세션에서 토큰 삭제
        // TODO: 2/17/24 AccessToken만 구현하면 token blacklist 등록
        return MyCafeResponse.success();
    }

}
