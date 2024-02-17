package com.cafe.service.admin.impl;

import com.cafe.service.admin.vo.EncryptedSignUpForm;
import org.springframework.transaction.annotation.Transactional;


public interface AdminCreator {

    @Transactional
    void create(EncryptedSignUpForm form);

}
