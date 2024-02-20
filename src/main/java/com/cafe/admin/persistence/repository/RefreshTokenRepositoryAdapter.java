package com.cafe.admin.persistence.repository;

import com.cafe.admin.persistence.dto.AdminIdRefreshTokenDto;
import com.cafe.admin.service.impl.RefreshTokenDeleter;
import com.cafe.admin.service.impl.RefreshTokenReader;
import com.cafe.admin.service.impl.RefreshTokenUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenReader, RefreshTokenUpdater, RefreshTokenDeleter {
    private final RefreshTokenRepository repository;

    @Override
    public boolean exist(Long adminId) {
        return repository.exist(adminId);
    }

    @Override
    public Set<AdminIdRefreshTokenDto> readAll() {
        return repository.findAll();
    }

    @Override
    public void update(Long adminId, String refreshToken) {
        repository.put(adminId, refreshToken);
    }

    @Override
    public void delete(Long adminId) {
        repository.delete(adminId);
    }

}
