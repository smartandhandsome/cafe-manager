package com.cafe.service.admin.impl;

import com.cafe.service.admin.vo.Admin;
import org.springframework.transaction.annotation.Transactional;


public interface AdminReader {

    @Transactional(readOnly = true)
    boolean hasDuplicatedPhoneNumber(String phoneNumber);

    @Transactional(readOnly = true)
    Admin readByPhoneNumber(String phoneNumber);

    @Transactional(readOnly = true)
    Long readAdminIdByPhoneNumber(String phoneNumber);

}
