package com.cafe.service.admin.impl;

import com.cafe.persistence.admin.entity.AdminJpaEntity;
import com.cafe.persistence.admin.repository.AdminJpaRepository;
import com.cafe.service.admin.vo.EncryptedSignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminCreator {
    private final AdminJpaRepository adminJpaRepository;

    @Transactional
    public void create(EncryptedSignUpForm form) {
        AdminJpaEntity newEntity = AdminJpaEntity.builder()
                .phoneNumber(form.phoneNumber())
                .encodedPassword(form.encodedPassword())
                .build();
        adminJpaRepository.save(newEntity);
    }
}
