package com.cafe.common.model;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public record AdminAuthorization(
        Long adminId
) {
}
