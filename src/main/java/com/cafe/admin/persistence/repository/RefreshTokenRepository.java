package com.cafe.admin.persistence.repository;

import com.cafe.admin.persistence.dto.AdminIdRefreshTokenDto;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class RefreshTokenRepository {
    private static final Map<Long, String> REPOSITORY = new ConcurrentHashMap<>();

    public void put(Long adminId, String refreshToken) {
        REPOSITORY.put(adminId, refreshToken);
    }

    public boolean exist(Long adminId) {
        return REPOSITORY.containsKey(adminId);
    }

    public void delete(Long adminId) {
        REPOSITORY.remove(adminId);
    }

    public Set<AdminIdRefreshTokenDto> findAll() {
        return REPOSITORY.entrySet()
                .stream()
                .map(entry -> new AdminIdRefreshTokenDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toUnmodifiableSet());
    }

}
