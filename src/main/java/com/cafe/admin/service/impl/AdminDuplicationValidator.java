package com.cafe.admin.service.impl;

import com.cafe.common.exception.DuplicatedResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

import static com.cafe.common.exception.ErrorCode.DUPLICATED_PHONE_NUMBER;

@Component
@RequiredArgsConstructor
public class AdminDuplicationValidator {

    private final AdminReader adminReader;

    public void validatePhoneNumber(String phoneNumber) {
        if (adminReader.hasDuplicatedPhoneNumber(phoneNumber)) {
            throw new DuplicatedResourceException(
                    DUPLICATED_PHONE_NUMBER,
                    MessageFormat.format("중복된 휴대폰 번호 [{0}]", phoneNumber)
            );
        }
    }

}
