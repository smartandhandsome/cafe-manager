package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductCategoryRegistrationFormFixture;
import com.cafe.product.service.vo.ProductCategoryUpdateForm;
import com.cafe.product.service.vo.ProductCategoryUpdateFormFixture;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductDetailInfoUpdateFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductValidatorTest {

    @InjectMocks
    ProductValidator productValidator;
    @Mock
    ProductCategoryDuplicationValidator productCategoryDuplicationValidator;
    @Mock
    ProductInfoDuplicationValidator productInfoDuplicationValidator;

    @Test
    void 카테고리_등록_폼을_검증할_수_있다() {
        // given
        ProductCategoryRegistrationForm productCategoryRegistrationForm = ProductCategoryRegistrationFormFixture.STANDARD.newInstance();

        // when
        productValidator.validate(productCategoryRegistrationForm);

        // then
        then(productCategoryDuplicationValidator).should(times(1)).validate(productCategoryRegistrationForm.name());
    }

    @Test
    void 상품_가격_정보_수정_폼을_검증할_수_있다() {
        // given
        ProductDetailInfoUpdateForm productInfoRegistrationForm = ProductDetailInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productValidator.validate(productInfoRegistrationForm);

        // then
        then(productInfoDuplicationValidator).should(times(1)).validate(productInfoRegistrationForm);
    }

    @Test
    void 상품_디테일_정보_수정_폼을_검증할_수_있다() {
        // given
        ProductDetailInfoUpdateForm productDetailInfoUpdateForm = ProductDetailInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productValidator.validate(productDetailInfoUpdateForm);

        // then
        then(productInfoDuplicationValidator).should(times(1)).validate(productDetailInfoUpdateForm);
    }

    @Test
    void 카테고리_수정_폼을_검증할_수_있다() {
        // given
        ProductCategoryUpdateForm productCategoryUpdateForm = ProductCategoryUpdateFormFixture.STANDARD.newInstance();

        // when
        productValidator.validate(productCategoryUpdateForm);

        // then
        then(productCategoryDuplicationValidator).should(times(1)).validate(productCategoryUpdateForm.name());
    }

}
