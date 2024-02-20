package com.cafe.admin.presentation;

import com.cafe.admin.presentation.request.LoginRequest;
import com.cafe.admin.presentation.response.LoginResponse;
import com.cafe.admin.service.AuthService;
import com.cafe.admin.service.vo.AuthToken;
import com.cafe.common.model.AdminAuthorization;
import com.cafe.common.model.MyCafeResponse;
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
        return MyCafeResponse.success(new LoginResponse(authToken.accessToken(), authToken.refreshToken()));
    }

    @Operation(description = "토큰 갱신 로그인")
    @PostMapping("/reissue")
    public MyCafeResponse<LoginResponse> requestReissue(
            AdminAuthorization adminAuthorization
    ) {
        AuthToken authToken = authService.reissue(adminAuthorization.adminId());
        return MyCafeResponse.success(new LoginResponse(authToken.accessToken(), authToken.refreshToken()));
    }

    @Operation(description = "관리자 로그아웃")
    @PostMapping("/logout")
    public MyCafeResponse<Void> requestLogin(
            AdminAuthorization adminAuthorization
    ) {
        authService.logout(adminAuthorization.adminId());
        return MyCafeResponse.success();
    }

}
