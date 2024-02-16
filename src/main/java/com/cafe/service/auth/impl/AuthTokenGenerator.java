package com.cafe.service.auth.impl;

import com.cafe.service.auth.vo.AuthToken;

public interface AuthTokenGenerator {

    AuthToken generate(String phoneNumber);

}
