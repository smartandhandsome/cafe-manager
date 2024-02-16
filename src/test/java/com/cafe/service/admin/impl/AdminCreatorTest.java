package com.cafe.service.admin.impl;

import com.cafe.persistence.admin.entity.AdminJpaEntity;
import com.cafe.persistence.admin.repository.AdminJpaRepository;
import com.cafe.service.admin.vo.EncryptedSignUpForm;
import com.cafe.service.admin.vo.EncryptedSignUpFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AdminCreatorTest {

    @InjectMocks
    AdminCreator adminCreator;
    @Mock
    AdminJpaRepository adminJpaRepository;

    @Test
    void 어드민을_생성할_수_있다() {
        // given
        EncryptedSignUpForm encryptedSignUpForm = EncryptedSignUpFormFixture.STANDARD.newInstance();

        // when
        adminCreator.create(encryptedSignUpForm);

        // then
        then(adminJpaRepository).should(times(1)).save(any(AdminJpaEntity.class));
    }

}
