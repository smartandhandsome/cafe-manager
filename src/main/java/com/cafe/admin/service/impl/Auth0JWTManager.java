package com.cafe.admin.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cafe.admin.persistence.dto.AdminIdRefreshTokenDto;
import com.cafe.admin.service.vo.AuthToken;
import com.cafe.common.exception.ExpiredTokenException;
import com.cafe.common.exception.IllegalTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Set;

import static com.cafe.common.exception.ErrorCode.EXPIRED_TOKEN;
import static com.cafe.common.exception.ErrorCode.ILLEGAL_TOKEN;

@Component
public class Auth0JWTManager implements AuthTokenGenerator, AuthTokenExtractor {

    public static final String ADMIN_ID = "adminId";

    private final RefreshTokenReader refreshTokenReader;
    private final RefreshTokenDeleter refreshTokenDeleter;
    private final RefreshTokenUpdater refreshTokenUpdater;

    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;

    public Auth0JWTManager(
            RefreshTokenReader refreshTokenReader, RefreshTokenDeleter refreshTokenDeleter, RefreshTokenUpdater refreshTokenUpdater,
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.access-token-validity-in-millis}") long accessTokenExpirationTime,
            @Value("${jwt.refresh-token-validity-in-millis}") long refreshTokenExpirationTime
    ) {
        this.refreshTokenReader = refreshTokenReader;
        this.refreshTokenDeleter = refreshTokenDeleter;
        this.refreshTokenUpdater = refreshTokenUpdater;
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.verifier = JWT.require(algorithm).build();
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    @Override
    public AuthToken generate(Long adminId) {
        String accessToken = getToken(adminId, accessTokenExpirationTime);
        String refreshToken = getToken(adminId, refreshTokenExpirationTime);
        refreshTokenUpdater.update(adminId, refreshToken);
        return new AuthToken(accessToken, refreshToken);
    }

    private String getToken(Long adminId, long exprirationTime) {
        Instant now = Instant.now();
        return JWT.create()
                .withClaim(ADMIN_ID, adminId)
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(exprirationTime))
                .sign(algorithm);
    }

    @Override
    public Long extractAdminId(final String jwt) {
        try {
            return verifier.verify(jwt)
                    .getClaim(ADMIN_ID)
                    .asLong();
        } catch (TokenExpiredException e) {
            throw new ExpiredTokenException(
                    EXPIRED_TOKEN,
                    "토큰이 만료되었습니다.",
                    e
            );
        } catch (JWTVerificationException e) {
            throw new IllegalTokenException(
                    ILLEGAL_TOKEN,
                    MessageFormat.format("잘못된 토큰[token: {0}]입니다.", jwt),
                    e
            );
        }
    }

    public void deleteAllExpired() {
        Set<AdminIdRefreshTokenDto> dtoSet = refreshTokenReader.readAll();
        dtoSet.forEach(
                dto -> {
                    try {
                        verifier.verify(dto.refreshToken());
                    } catch (TokenExpiredException e) {
                        refreshTokenDeleter.delete(dto.adminId());
                    }
                }
        );
    }
}

