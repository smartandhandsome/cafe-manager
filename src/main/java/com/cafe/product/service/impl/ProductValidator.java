package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    private final ProductCategoryDuplicationValidator productCategoryDuplicationValidator;

    @Transactional(readOnly = true)
    public void validate(ProductCategoryRegistrationForm productCategoryRegistrationForm) {
        productCategoryDuplicationValidator.validate(productCategoryRegistrationForm);
    }

}
