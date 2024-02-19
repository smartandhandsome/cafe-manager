package com.cafe.product.service;

import com.cafe.product.service.impl.ProductRegister;
import com.cafe.product.service.impl.ProductValidator;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductCategoryRegistrationFormFixture;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductInfoRegistrationFormFixture;
import com.cafe.product.service.vo.SizeRegistrationForm;
import com.cafe.product.service.vo.SizeRegistrationFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    ProductRegister productRegister;
    @Mock
    ProductValidator productValidator;

    @Test
    void 상품_카테고리를_등록할_수_있다() {
        // given
        ProductCategoryRegistrationForm productCategoryRegistrationForm = ProductCategoryRegistrationFormFixture.STANDARD.newInstance();

        // when
        productService.register(productCategoryRegistrationForm);

        // then1
        then(productValidator).should(times(1)).validate(productCategoryRegistrationForm);
        then(productRegister).should(times(1)).register(productCategoryRegistrationForm);
    }

    @Test
    void 상품을_등록할_수_있다() {
        // given
        ProductInfoRegistrationForm productInfoRegistrationForm = ProductInfoRegistrationFormFixture.STANDARD.newInstance();
        List<SizeRegistrationForm> sizeRegistrationForms = List.of(
                SizeRegistrationFormFixture.SMALL.newInstance(),
                SizeRegistrationFormFixture.LARGE.newInstance()
        );

        // when
        productService.register(
                productInfoRegistrationForm,
                sizeRegistrationForms
        );

        // then
        then(productRegister).should(times(1)).register(productInfoRegistrationForm, sizeRegistrationForms);
    }

}
