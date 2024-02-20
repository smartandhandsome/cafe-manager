package com.cafe.admin.service.impl;

import com.cafe.admin.persistence.dto.AdminIdRefreshTokenDto;

import java.util.Set;

public interface RefreshTokenReader {

    boolean exist(Long adminId);

    Set<AdminIdRefreshTokenDto> readAll();

}
