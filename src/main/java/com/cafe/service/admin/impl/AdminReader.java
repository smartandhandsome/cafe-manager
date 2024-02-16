package com.cafe.service.admin.impl;

import com.cafe.persistence.admin.repository.AdminJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminReader {

    private final AdminJpaRepository adminJpaRepository;

    @Transactional(readOnly = true)
    public boolean hasDuplicatedPhoneNumber(String phoneNumber) {
        return adminJpaRepository.existsByPhoneNumber(phoneNumber);
    }

}
