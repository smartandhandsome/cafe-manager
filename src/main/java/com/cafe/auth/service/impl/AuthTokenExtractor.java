package com.cafe.auth.service.impl;

public interface AuthTokenExtractor {

    Long extractAdminId(String jwt);

}
