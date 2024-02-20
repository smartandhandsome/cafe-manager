package com.cafe.product.service.impl;

import com.cafe.product.service.vo.SizeRegistrationForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductSizeCreator {

    @Transactional
    void createAll(Long productInfoId, List<SizeRegistrationForm> sizeRegistrationForms);

}
