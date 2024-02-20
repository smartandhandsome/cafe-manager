package com.cafe.product.service.impl;

import org.springframework.transaction.annotation.Transactional;

public interface ProductCategoryDeleter {

    @Transactional
    void deleteByProductCategoryId(Long productCategoryId);

}
