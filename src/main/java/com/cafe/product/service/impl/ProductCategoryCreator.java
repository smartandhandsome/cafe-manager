package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductCategoryCreator {

    @Transactional
    void create(ProductCategoryRegistrationForm productInfoRegistrationForm);

}
