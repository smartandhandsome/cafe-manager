package com.cafe.service.auth.vo;

public enum AuthTokenFixture {
    STANDARD(Constants.TOKEN),
    ILLEGAL(Constants.ILLEGAL_TOKEN),
    ;


    private final String value;

    AuthTokenFixture(String value) {
        this.value = value;
    }

    public AuthToken newInstance() {
        return new AuthToken(value);
    }

    private static final class Constants {
        private static final String TOKEN = "Bearer header.payload.signature";
        private static final String ILLEGAL_TOKEN = "Illegal Token";
    }

}
