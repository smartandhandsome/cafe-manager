package com.cafe.admin.service.vo;

public enum AuthTokenFixture {
    STANDARD(Constants.ACCESS_TOKEN, Constants.REFRESH_TOKEN),
    ILLEGAL(Constants.ILLEGAL_TOKEN, Constants.ILLEGAL_TOKEN),
    ;

    private final String accessToken;
    private final String refreshToken;

    AuthTokenFixture(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public AuthToken newInstance() {
        return new AuthToken(accessToken, refreshToken);
    }

    private static final class Constants {
        public static final String REFRESH_TOKEN = "Bearer header.payload.signature";
        private static final String ACCESS_TOKEN = "Bearer header.payload.signature";
        private static final String ILLEGAL_TOKEN = "Illegal Token";
    }

}
