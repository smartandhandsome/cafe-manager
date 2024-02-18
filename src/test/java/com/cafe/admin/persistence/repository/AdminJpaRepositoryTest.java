package com.cafe.admin.persistence.repository;

import com.cafe.admin.persistence.entity.AdminJpaEntity;
import com.cafe.admin.persistence.entity.AdminJpaEntityFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AdminJpaRepositoryTest {

    @Autowired
    AdminJpaRepository adminJpaRepository;

    AdminJpaEntity entity;
    AdminJpaEntity notExistedEntity;

    @BeforeEach
    void setUp() {
        notExistedEntity = AdminJpaEntityFixture.NOT_EXISTED.newInstance();
        entity = adminJpaRepository.save(AdminJpaEntityFixture.STANDARD.newInstance());
    }

    @Nested
    class 특정_휴대폰_번호를_갖는_데이터의_존재여부를_확인할_수_있다 {

        @Test
        void 데이터_존재할_때() {
            // given
            adminJpaRepository.save(entity);

            // when
            boolean isExisted = adminJpaRepository.existsByPhoneNumber(entity.getPhoneNumber());

            // then
            assertThat(isExisted).isTrue();
        }

        @Test
        void 데이터_존재하지_않을_때() {
            // given

            // when
            boolean isExisted = adminJpaRepository.existsByPhoneNumber(notExistedEntity.getPhoneNumber());

            // then
            assertThat(isExisted).isFalse();
        }

    }

    @Nested
    class 특정_휴대폰_번호를_갖는_데이터를_조회할_수_있다 {

        @Test
        void 데이터_존재할_때() {
            // given

            // when
            Optional<AdminJpaEntity> found = adminJpaRepository.findByPhoneNumber(entity.getPhoneNumber());

            // then
            assertThat(found).isPresent();
            found.ifPresent(
                    foundEntity -> assertThat(foundEntity)
                            .usingRecursiveComparison()
                            .isEqualTo(entity)
            );
        }

        @Test
        void 데이터_존재하지_않을_때() {
            // given

            // when
            Optional<AdminJpaEntity> found = adminJpaRepository.findByPhoneNumber(notExistedEntity.getPhoneNumber());

            // then
            assertThat(found).isNotPresent();
        }

    }

    @Nested
    class 특정_휴대폰_번호를_갖는_데이터의_아이디를_조회할_수_있다 {

        @Test
        void 데이터_존재_할_때() {
            // given

            // when
            Optional<Long> found = adminJpaRepository.findAdminIdByPhoneNumber(entity.getPhoneNumber());

            // then
            assertThat(found).isPresent();
            found.ifPresent(
                    foundAdminId -> assertThat(foundAdminId)
                            .isEqualTo(entity.getAdminId())
            );
        }

        @Test
        void 데이터_존재하지_않을_때() {
            // given

            // when
            Optional<Long> found = adminJpaRepository.findAdminIdByPhoneNumber(notExistedEntity.getPhoneNumber());

            // then
            assertThat(found).isNotPresent();
        }

    }


}
