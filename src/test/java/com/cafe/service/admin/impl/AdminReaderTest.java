package com.cafe.service.admin.impl;

import com.cafe.persistence.admin.repository.AdminJpaRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AdminReaderTest {

    @InjectMocks
    AdminReader adminReader;
    @Mock
    AdminJpaRepository adminJpaRepository;

    @ParameterizedTest(name = "{1}")
    @CsvSource({"true, '휴대폰 번호 중복'", "false, '휴대폰 번호 중복 되지 않음'"})
    void 중복된_휴대폰_번호가_있는지_확인할_수_있다(boolean isExisted, String message) {
        // given
        String phoneNumber = "010-1234-5678";

        given(adminJpaRepository.existsByPhoneNumber(phoneNumber)).willReturn(isExisted);

        // when
        boolean hasDuplicatedPhoneNumber = adminReader.hasDuplicatedPhoneNumber(phoneNumber);

        // then
        assertThat(hasDuplicatedPhoneNumber).isEqualTo(isExisted);
    }

}
