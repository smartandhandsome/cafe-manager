package com.cafe.admin.service.impl;

public interface AuthTokenExtractor {

    Long extractAdminId(String jwt);

}
