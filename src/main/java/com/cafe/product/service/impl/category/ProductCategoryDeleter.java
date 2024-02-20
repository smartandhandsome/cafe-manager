package com.cafe.product.service.impl.category;

import org.springframework.transaction.annotation.Transactional;

public interface ProductCategoryDeleter {

    @Transactional
    void deleteByProductCategoryId(Long productCategoryId);

}
