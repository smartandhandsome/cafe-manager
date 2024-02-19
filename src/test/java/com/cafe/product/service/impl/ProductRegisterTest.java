package com.cafe.product.service.impl;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductInfoJpaEntityFixture;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductRegisterTest {

    @InjectMocks
    ProductRegister productRegister;
    @Mock
    private ProductInfoCreator productInfoCreator;
    @Mock
    private ProductSizeCreator productSizeCreator;
    @Mock
    private ProductCategoryCreator productCategoryCreator;

    @Test
    void 상품_카테고리를_생성할_수_있다() {
        // given
        ProductCategoryRegistrationForm productCategoryRegistrationForm = ProductCategoryRegistrationFormFixture.STANDARD.newInstance();

        // when
        productRegister.register(productCategoryRegistrationForm);

        // then
        then(productCategoryCreator).should(times(1)).create(productCategoryRegistrationForm);
    }

    @Test
    void 상품_및_추가옵션를_생성할_수_있다() {
        // given
        ProductInfoRegistrationForm productInfoRegistrationForm = ProductInfoRegistrationFormFixture.STANDARD.newInstance();
        List<SizeRegistrationForm> sizeRegistrationFormList = List.of(
                SizeRegistrationFormFixture.SMALL.newInstance(),
                SizeRegistrationFormFixture.LARGE.newInstance()
        );
        ProductInfoJpaEntity productInfoJpaEntity = ProductInfoJpaEntityFixture.STANDARD.newInstance();

        given(productInfoCreator.create(productInfoRegistrationForm)).willReturn(productInfoJpaEntity);

        // when
        productRegister.register(productInfoRegistrationForm, sizeRegistrationFormList);

        // then
        then(productSizeCreator).should(times(1)).createAll(productInfoJpaEntity, sizeRegistrationFormList);
    }

}
