package com.cafe.service.auth.impl;

public interface AuthTokenExtractor {

    Long extractAdminId(String jwt);

}
