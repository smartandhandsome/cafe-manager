package com.cafe.product.service;

import com.cafe.product.service.impl.ProductChanger;
import com.cafe.product.service.impl.ProductRegister;
import com.cafe.product.service.impl.ProductValidator;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductCategoryRegistrationFormFixture;
import com.cafe.product.service.vo.ProductCategoryUpdateForm;
import com.cafe.product.service.vo.ProductCategoryUpdateFormFixture;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductDetailInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductInfoRegistrationFormFixture;
import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import com.cafe.product.service.vo.ProductPriceInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.ProductSizeInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductSizePriceUpdateForm;
import com.cafe.product.service.vo.ProductSizePriceUpdateFormFixture;
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
    @Mock
    ProductChanger productChanger;

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
        List<SizeRegistrationForm> sizeRegistrationForms = List.of(SizeRegistrationFormFixture.SMALL.newInstance(), SizeRegistrationFormFixture.LARGE.newInstance());

        // when
        productService.register(productInfoRegistrationForm, sizeRegistrationForms);

        // then
        then(productRegister).should(times(1)).register(productInfoRegistrationForm, sizeRegistrationForms);
    }

    @Test
    void 상품_가격_정보를_수정할_수_있다() {
        // given
        ProductPriceInfoUpdateForm productPriceInfoUpdateForm = ProductPriceInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productService.update(productPriceInfoUpdateForm);

        // then
        then(productChanger).should(times(1)).change(productPriceInfoUpdateForm);
    }

    @Test
    void 상품_디테일_정보를_수정할_수_있다() {
        // given
        ProductDetailInfoUpdateForm productDetailInfoUpdateForm = ProductDetailInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productService.update(productDetailInfoUpdateForm);

        // then
        then(productValidator).should(times(1)).validate(productDetailInfoUpdateForm);
        then(productChanger).should(times(1)).change(productDetailInfoUpdateForm);
    }

    @Test
    void 상품_카테고리를_수정할_수_있다() {
        // given
        ProductCategoryUpdateForm productCategoryUpdateForm = ProductCategoryUpdateFormFixture.STANDARD.newInstance();

        // when
        productService.update(productCategoryUpdateForm);

        // then
        then(productValidator).should(times(1)).validate(productCategoryUpdateForm);
        then(productChanger).should(times(1)).change(productCategoryUpdateForm);
    }

    @Test
    void 상품_사이즈_가격을_수정할_수_있다() {
        // given
        ProductSizePriceUpdateForm productSizePriceUpdateForm = ProductSizePriceUpdateFormFixture.STANDARD.newInstance();

        // when
        productService.update(productSizePriceUpdateForm);

        // then
        then(productChanger).should(times(1)).change(productSizePriceUpdateForm);
    }

    @Test
    void 상품_사이즈_정보를_수정할_수_있다() {
        // given
        ProductSizeInfoUpdateForm productSizeInfoUpdateForm = ProductSizeInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productService.update(productSizeInfoUpdateForm);

        // then
        then(productChanger).should(times(1)).change(productSizeInfoUpdateForm);
    }

}
