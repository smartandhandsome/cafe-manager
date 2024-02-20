package com.cafe.admin.persistence.repository;

import com.cafe.admin.persistence.dto.AdminIdRefreshTokenDto;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RefreshTokenRepositoryAdapterTest {

    @InjectMocks
    private RefreshTokenRepositoryAdapter refreshTokenRepositoryAdapter;
    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    void 데이터가_존재하는지_확인할_수_있다() {
        // given
        given(refreshTokenRepository.exist(anyLong())).willReturn(true);

        // when
        boolean exists = refreshTokenRepositoryAdapter.exist(1L);

        // then
        assertThat(exists).isTrue();
        then(refreshTokenRepository).should(times(1)).exist(anyLong());
    }

    @Test
    void 데이터를_모두_불러올_수_있다() {
        // given
        Set<AdminIdRefreshTokenDto> expectedSet = new HashSet<>();
        expectedSet.add(new AdminIdRefreshTokenDto(1L, "refreshToken"));
        given(refreshTokenRepository.findAll()).willReturn(expectedSet);

        // when
        Set<AdminIdRefreshTokenDto> resultSet = refreshTokenRepositoryAdapter.readAll();

        // then
        assertThat(resultSet).isEqualTo(expectedSet);
        then(refreshTokenRepository).should(times(1)).findAll();
    }

    @Test
    void 데이터를_수정할_수_있다() {
        // given

        // when
        refreshTokenRepositoryAdapter.update(1L, "newRefreshToken");

        // then
        then(refreshTokenRepository).should().put(1L, "newRefreshToken");
    }

    @Test
    void 데이터를_삭제할_수_있다() {
        // given

        // When
        refreshTokenRepositoryAdapter.delete(1L);

        // Then
        then(refreshTokenRepository).should().delete(1L);
    }
}

