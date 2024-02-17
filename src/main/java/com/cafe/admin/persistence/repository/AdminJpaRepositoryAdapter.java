package com.cafe.admin.persistence.repository;

import com.cafe.admin.persistence.entity.AdminJpaEntity;
import com.cafe.admin.service.impl.AdminCreator;
import com.cafe.admin.service.impl.AdminReader;
import com.cafe.admin.service.vo.Admin;
import com.cafe.admin.service.vo.EncryptedSignUpForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class AdminJpaRepositoryAdapter implements AdminReader, AdminCreator {

    public static final String ADMIN_NOT_FOUND_PHONE_NUMBER = "해당 휴대폰 번호[phoneNumber: {0}]를 갖는 관리자가 존재하지 않습니다.";

    private final AdminJpaRepository adminJpaRepository;

    @Override
    public boolean hasDuplicatedPhoneNumber(String phoneNumber) {
        return adminJpaRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Admin readByPhoneNumber(String phoneNumber) {
        AdminJpaEntity entity = adminJpaRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format(
                                ADMIN_NOT_FOUND_PHONE_NUMBER,
                                phoneNumber
                        )
                ));
        return convertToAdmin(entity);
    }

    @Override
    public Long readAdminIdByPhoneNumber(String phoneNumber) {
        return adminJpaRepository.findAdminIdByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format(
                                ADMIN_NOT_FOUND_PHONE_NUMBER,
                                phoneNumber
                        )
                ));

    }

    @Override
    public void create(EncryptedSignUpForm signUpForm) {
        adminJpaRepository.save(convertToEntity(signUpForm));
    }

    private Admin convertToAdmin(AdminJpaEntity entity) {
        return new Admin(
                entity.getPhoneNumber(),
                entity.getEncodedPassword()
        );
    }

    private AdminJpaEntity convertToEntity(EncryptedSignUpForm signUpForm) {
        return AdminJpaEntity.builder()
                .phoneNumber(signUpForm.phoneNumber())
                .encodedPassword(signUpForm.encodedPassword())
                .build();
    }

}
