package com.cafe.product.service.impl.size;

import org.springframework.transaction.annotation.Transactional;

public interface ProductSizeDeleter {

    @Transactional
    void deleteByProductSizeId(Long productSizeId);

    @Transactional
    void deleteAllByProductInfoId(Long productInfoId);

}
