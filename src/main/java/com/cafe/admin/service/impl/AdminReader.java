package com.cafe.admin.service.impl;

import com.cafe.admin.service.vo.Admin;
import org.springframework.transaction.annotation.Transactional;


public interface AdminReader {

    @Transactional(readOnly = true)
    boolean hasDuplicatedPhoneNumber(String phoneNumber);

    @Transactional(readOnly = true)
    Admin readByPhoneNumber(String phoneNumber);

    @Transactional(readOnly = true)
    Long readAdminIdByPhoneNumber(String phoneNumber);

}
