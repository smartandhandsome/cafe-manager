package com.cafe.product.service.impl;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductInfoCreator {

    @Transactional
    ProductInfoJpaEntity create(ProductInfoRegistrationForm productInfoRegistrationForm);

}
