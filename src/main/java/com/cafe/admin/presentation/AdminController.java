package com.cafe.admin.presentation;

import com.cafe.admin.presentation.request.SignUpRequest;
import com.cafe.admin.service.AdminService;
import com.cafe.common.model.MyCafeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin", description = "관리자 API")
@RestController
@RequestMapping("/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(description = "관리자 회원가입")
    @PostMapping
    public MyCafeResponse<Void> requestSignUp(@RequestBody @Valid SignUpRequest signupRequest) {
        adminService.signUp(signupRequest.toSignUpForm());
        return MyCafeResponse.success();
    }

}
