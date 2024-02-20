package com.cafe.admin.persistence.repository;

import com.cafe.admin.persistence.dto.AdminIdRefreshTokenDto;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RefreshTokenRepositoryTest {

    private RefreshTokenRepository refreshTokenRepository = new RefreshTokenRepository();

    @Test
    void 데이터를_저장_및_수정할_수_있다() {
        // given
        Long adminId = 1L;
        String refreshToken = "refreshToken";

        // when
        refreshTokenRepository.put(adminId, refreshToken);

        // then
        assertThat(refreshTokenRepository.exist(adminId)).isTrue();
    }

    @Test
    void 데이터를_삭제할_수_있다() {
        // given
        Long adminId = 1L;
        String refreshToken = "refreshToken";

        // when
        refreshTokenRepository.put(adminId, refreshToken);
        refreshTokenRepository.delete(adminId);

        // then
        assertThat(refreshTokenRepository.exist(adminId)).isFalse();
    }

    @Test
    void 모든_데이터를_불러올_수_있다() {
        // given
        refreshTokenRepository.put(1L, "refreshToken1");
        refreshTokenRepository.put(2L, "refreshToken2");

        // when
        Set<AdminIdRefreshTokenDto> tokens = refreshTokenRepository.findAll();

        // then
        assertThat(tokens).hasSize(2);
    }

}
