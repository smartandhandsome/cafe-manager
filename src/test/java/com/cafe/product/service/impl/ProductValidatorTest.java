package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductCategoryRegistrationFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductValidatorTest {

    @InjectMocks
    ProductValidator productValidator;
    @Mock
    ProductCategoryDuplicationValidator productCategoryDuplicationValidator;

    @Transactional(readOnly = true)
    public void validate(ProductCategoryRegistrationForm productCategoryRegistrationForm) {

    }

    @Test
    void 카테고리_등록_폼을_검증할_수_있다() {
        // given
        ProductCategoryRegistrationForm productCategoryRegistrationForm = ProductCategoryRegistrationFormFixture.STANDARD.newInstance();

        // when
        productValidator.validate(productCategoryRegistrationForm);

        // then
        then(productCategoryDuplicationValidator).should(times(1)).validate(productCategoryRegistrationForm);
    }

}
