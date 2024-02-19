package com.cafe.product.service.impl;

import com.cafe.common.exception.DuplicatedResourceException;
import com.cafe.common.exception.ErrorCode;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class ProductCategoryDuplicationValidator {

    private final ProductCategoryReader productCategoryReader;

    @Transactional(readOnly = true)
    public void validate(ProductCategoryRegistrationForm productCategoryRegistrationForm) {
        if (productCategoryReader.existsByName(productCategoryRegistrationForm.name())) {
            throw new DuplicatedResourceException(
                    ErrorCode.DUPLICATED_CATEGORY_NAME,
                    MessageFormat.format("중복된 카테고리 이름 [Category.name(): {0}]", productCategoryRegistrationForm.name())
            );
        }
    }
}
