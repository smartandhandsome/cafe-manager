package com.cafe.presentation.admin;

import com.cafe.presentation.admin.request.SignUpRequest;
import com.cafe.common.MyCafeResponse;
import com.cafe.service.admin.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin", description = "회원 관련 API")
@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(description = "관리자 회원가입")
    @PostMapping
    public MyCafeResponse<Void> signUp(@RequestBody @Valid SignUpRequest signupRequest) {
        adminService.signUp(signupRequest.toSignUpForm());
        return MyCafeResponse.success();
    }

}
