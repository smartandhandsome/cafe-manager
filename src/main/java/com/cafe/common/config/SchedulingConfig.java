package com.cafe.common.config;

import com.cafe.admin.service.impl.Auth0JWTManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfig {
    private final Auth0JWTManager auth0JWTManager;

    @Scheduled(cron = "0 0 0 * * *")
    private void evict() {
        auth0JWTManager.deleteAllExpired();
    }

}
