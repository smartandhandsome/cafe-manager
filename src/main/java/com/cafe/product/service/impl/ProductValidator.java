package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    private final ProductCategoryDuplicationValidator productCategoryDuplicationValidator;
    private final ProductInfoDuplicationValidator productInfoDuplicationValidator;

    @Transactional(readOnly = true)
    public void validate(ProductCategoryRegistrationForm productCategoryRegistrationForm) {
        productCategoryDuplicationValidator.validate(productCategoryRegistrationForm);
    }

    public void validate(ProductInfoRegistrationForm productInfoRegistrationForm) {
        // TODO: 2/19/24 test
        productInfoDuplicationValidator.validate(productInfoRegistrationForm);
    }

    public void validate(ProductDetailInfoUpdateForm productDetailInfoUpdateForm) {
        productInfoDuplicationValidator.validate(productDetailInfoUpdateForm);
    }
}
