package com.cafe.admin.service.impl;

import com.cafe.admin.service.vo.EncryptedSignUpForm;
import org.springframework.transaction.annotation.Transactional;


public interface AdminCreator {

    @Transactional
    void create(EncryptedSignUpForm form);

}
