package com.cafe.admin.service.vo;

public record EncryptedSignUpForm(
        String phoneNumber,
        String encodedPassword
) {
}
