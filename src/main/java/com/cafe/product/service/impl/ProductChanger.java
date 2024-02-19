package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductCategoryUpdateForm;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import com.cafe.product.service.vo.ProductSizePriceUpdateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductChanger {

    private final ProductInfoChanger productInfoChanger;
    private final ProductCategoryChanger productCategoryChanger;
    private final ProductSizeChanger productSizeChanger;

    @Transactional
    public void change(ProductPriceInfoUpdateForm productPriceInfoUpdateForm) {
        productInfoChanger.change(productPriceInfoUpdateForm);
    }

    @Transactional
    public void change(ProductDetailInfoUpdateForm productDetailInfoUpdateForm) {
        productInfoChanger.change(productDetailInfoUpdateForm);
    }

    @Transactional
    public void change(ProductCategoryUpdateForm productCategoryUpdateForm) {
        productCategoryChanger.change(productCategoryUpdateForm);
    }

    @Transactional
    public void change(ProductSizePriceUpdateForm productSizePriceUpdateForm) {
        productSizeChanger.change(productSizePriceUpdateForm);
    }
}
