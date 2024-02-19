package com.cafe.common.model;

import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

public abstract class AdminAuthorizationControllerTest extends BaseControllerTest {
    protected AdminAuthorization authorization;
    protected String authorizationHeader = "Bearer header.payload.signature";

    @BeforeEach
    void setUp() {
        Long adminId = 1L;
        authorization = new AdminAuthorization(adminId);

        given(authTokenExtractor.extractAdminId(anyString())).willReturn(adminId);
        given(resolver.resolveArgument(any(), any(), any(), any())).willReturn(authorization);
    }
}
