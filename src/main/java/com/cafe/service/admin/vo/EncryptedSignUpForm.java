package com.cafe.service.admin.vo;

public record EncryptedSignUpForm(
        String phoneNumber,
        String encodedPassword
) {
}
