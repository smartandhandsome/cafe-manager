package com.cafe.admin.service.impl;

import com.cafe.admin.service.vo.AuthToken;

public interface AuthTokenGenerator {

    AuthToken generate(String phoneNumber);

}
