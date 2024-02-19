package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.ProductSizePriceUpdateForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductSizeChanger {

    @Transactional
    void change(ProductSizePriceUpdateForm productSizePriceUpdateForm);

    @Transactional
    void change(ProductSizeInfoUpdateForm productSizeInfoUpdateForm);

}
