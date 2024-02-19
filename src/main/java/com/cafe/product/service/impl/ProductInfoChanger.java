package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductInfoChanger {

    @Transactional
    void change(ProductPriceInfoUpdateForm productPriceInfoUpdateForm);

    @Transactional
    void change(ProductDetailInfoUpdateForm productDetailInfoUpdateForm);
}
