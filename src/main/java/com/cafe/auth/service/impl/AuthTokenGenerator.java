package com.cafe.auth.service.impl;

import com.cafe.auth.service.vo.AuthToken;

public interface AuthTokenGenerator {

    AuthToken generate(String phoneNumber);

}
