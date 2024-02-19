package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductCategoryUpdateForm;
import com.cafe.product.service.vo.ProductCategoryUpdateFormFixture;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductDetailInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import com.cafe.product.service.vo.ProductPriceInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.ProductSizeInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductSizePriceUpdateForm;
import com.cafe.product.service.vo.ProductSizePriceUpdateFormFixture;
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
class ProductChangerTest {

    @InjectMocks
    ProductChanger productChanger;
    @Mock
    ProductInfoChanger productInfoChanger;
    @Mock
    ProductCategoryChanger productCategoryChanger;
    @Mock
    ProductSizeChanger productSizeChanger;

    @Test
    void 상품_가격_정보를_변경할_수_있다() {
        // given
        ProductPriceInfoUpdateForm productPriceInfoUpdateForm = ProductPriceInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productChanger.change(productPriceInfoUpdateForm);

        // then
        then(productInfoChanger).should(times(1)).change(productPriceInfoUpdateForm);
    }

    @Test
    void 상품_디테일_정보를_변경할_수_있다() {
        // given
        ProductDetailInfoUpdateForm productDetailInfoUpdateForm = ProductDetailInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productChanger.change(productDetailInfoUpdateForm);

        // then
        then(productInfoChanger).should(times(1)).change(productDetailInfoUpdateForm);
    }

    @Test
    void 상품_카테고리를_변경할_수_있다() {
        // given
        ProductCategoryUpdateForm productCategoryUpdateForm = ProductCategoryUpdateFormFixture.STANDARD.newInstance();

        // when
        productChanger.change(productCategoryUpdateForm);

        // then
        then(productCategoryChanger).should(times(1)).change(productCategoryUpdateForm);
    }

    @Test
    void 상품_사이즈_가격을_수정을_할_수_있다() {
        // given
        ProductSizePriceUpdateForm productSizePriceUpdateForm = ProductSizePriceUpdateFormFixture.STANDARD.newInstance();

        // when
        productChanger.change(productSizePriceUpdateForm);

        // then
        then(productSizeChanger).should(times(1)).change(productSizePriceUpdateForm);
    }

    @Test
    void 상품_사이즈_정보를_수정을_할_수_있다() {
        // given
        ProductSizeInfoUpdateForm productSizeInfoUpdateForm = ProductSizeInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productChanger.change(productSizeInfoUpdateForm);

        // then
        then(productSizeChanger).should(times(1)).change(productSizeInfoUpdateForm);
    }

}
