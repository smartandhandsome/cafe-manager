package com.cafe.admin.persistence.repository;

import com.cafe.admin.persistence.entity.AdminJpaEntity;
import com.cafe.admin.persistence.entity.AdminJpaEntityFixture;
import com.cafe.admin.service.vo.Admin;
import com.cafe.admin.service.vo.EncryptedSignUpForm;
import com.cafe.admin.service.vo.EncryptedSignUpFormFixture;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AdminJpaRepositoryAdapterTest {

    @InjectMocks
    private AdminJpaRepositoryAdapter adapter;
    @Mock
    private AdminJpaRepository adminJpaRepository;

    @Test
    void 관리자_계정을를_생성할_수_있다() {
        // given
        EncryptedSignUpForm encryptedSignUpForm = EncryptedSignUpFormFixture.STANDARD.newInstance();

        // when
        adapter.create(encryptedSignUpForm);

        // then
        then(adminJpaRepository)
                .should(times(1))
                .save(any(AdminJpaEntity.class));
    }

    @Nested
    class 휴대폰_번호_중복_검사를_할_수_있다 {

        @Test
        void 중복이_존재_할_때() {
            // given
            String duplicatedPhoneNumber = "010-1234-5678";
            given(adminJpaRepository.existsByPhoneNumber(duplicatedPhoneNumber))
                    .willReturn(true);

            // when
            boolean result = adapter.hasDuplicatedPhoneNumber(duplicatedPhoneNumber);

            // then
            assertThat(result).isTrue();
        }

        @Test
        void 중복이_존재하지_않을_때() {
            // given
            String duplicatedPhoneNumber = "010-1234-5678";
            given(adminJpaRepository.existsByPhoneNumber(duplicatedPhoneNumber))
                    .willReturn(false);

            // when
            boolean result = adapter.hasDuplicatedPhoneNumber(duplicatedPhoneNumber);

            // then
            assertThat(result).isFalse();
        }

    }

    @Nested
    class 휴대폰_번호로_관리자_계정을_조회할_수_있다 {

        @Test
        void 데이터가_존재_할_때() {
            // given
            AdminJpaEntity entity = AdminJpaEntityFixture.STANDARD.newInstance();
            String phoneNumber = entity.getPhoneNumber();

            given(adminJpaRepository.findByPhoneNumber(phoneNumber))
                    .willReturn(Optional.of(entity));

            // when
            Admin admin = adapter.readByPhoneNumber(phoneNumber);

            // then
            assertThat(admin.phoneNumber())
                    .isEqualTo(entity.getPhoneNumber());
            assertThat(admin.encodedPassword())
                    .isEqualTo(entity.getEncodedPassword());
        }

        @Test
        void 데이터가_존재하지_않을_때() {
            // given
            AdminJpaEntity entity = AdminJpaEntityFixture.STANDARD.newInstance();
            String phoneNumber = entity.getPhoneNumber();

            given(adminJpaRepository.findByPhoneNumber(phoneNumber)).willReturn(Optional.empty());

            // when, then
            assertThatThrownBy(() -> adapter.readByPhoneNumber(phoneNumber))
                    .isExactlyInstanceOf(EntityNotFoundException.class);
        }

    }

    @Nested
    class 휴대폰_번호로_관리자_아이디를_조회할_수_있다 {

        @Test
        void 데이터가_존재_할_때() {
            // given
            AdminJpaEntity entity = AdminJpaEntityFixture.STANDARD.newInstance();
            String phoneNumber = entity.getPhoneNumber();
            Long adminId = entity.getAdminId();

            given(adminJpaRepository.findAdminIdByPhoneNumber(phoneNumber))
                    .willReturn(Optional.of(adminId));

            // when
            Long readAdminId = adapter.readAdminIdByPhoneNumber(phoneNumber);

            // then
            assertThat(readAdminId)
                    .isEqualTo(adminId);
        }

        @Test
        void 데이터가_존재하지_않을_때() {
            // given
            String notExistedPhoneNumber = "010-9876-5432";

            given(adminJpaRepository.findAdminIdByPhoneNumber(notExistedPhoneNumber))
                    .willReturn(Optional.empty());

            // when, then
            assertThatThrownBy(() -> adapter.readAdminIdByPhoneNumber(notExistedPhoneNumber))
                    .isExactlyInstanceOf(EntityNotFoundException.class);

        }

    }


}
