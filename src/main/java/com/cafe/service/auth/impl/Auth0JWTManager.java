package com.cafe.service.auth.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cafe.common.exception.ExpiredTokenException;
import com.cafe.common.exception.IllegalTokenException;
import com.cafe.service.admin.impl.AdminReader;
import com.cafe.service.auth.vo.AuthToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Instant;

import static com.cafe.common.exception.ErrorCode.EXPIRED_TOKEN;
import static com.cafe.common.exception.ErrorCode.ILLEGAL_TOKEN;

@Component
public class Auth0JWTManager implements AuthTokenGenerator, AuthTokenExtractor {

    public static final String ADMIN_ID = "adminId";
    private final AdminReader adminReader;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final long tokenExpirationTime;

    public Auth0JWTManager(
            AdminReader adminReader,
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.validity-in-millis}") long tokenExpirationTime
    ) {
        this.adminReader = adminReader;
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.verifier = JWT.require(algorithm).build();
        this.tokenExpirationTime = tokenExpirationTime;
    }

    @Override
    public AuthToken generate(String phoneNumber) {
        Long adminId = adminReader.readAdminIdByPhoneNumber(phoneNumber);

        Instant now = Instant.now();
        String token = JWT.create()
                .withClaim(ADMIN_ID, adminId)
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(tokenExpirationTime))
                .sign(algorithm);
        return new AuthToken(token);
    }

    @Override
    public Long extractAdminId(final String jwt) {
        try {
            return verifier
                    .verify(jwt)
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
}

