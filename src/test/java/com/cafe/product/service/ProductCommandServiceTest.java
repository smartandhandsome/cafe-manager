package com.cafe.product.service;

import com.cafe.product.service.impl.category.ProductCategoryChanger;
import com.cafe.product.service.impl.category.ProductCategoryCreator;
import com.cafe.product.service.impl.category.ProductCategoryDuplicationValidator;
import com.cafe.product.service.impl.category.ProductCategoryIntegrationDeleter;
import com.cafe.product.service.impl.info.ProductInfoChanger;
import com.cafe.product.service.impl.info.ProductInfoDuplicationValidator;
import com.cafe.product.service.impl.info.ProductInfoIntegrationCreator;
import com.cafe.product.service.impl.info.ProductInfoIntegrationDeleter;
import com.cafe.product.service.impl.info.ProductInfoPreprocessor;
import com.cafe.product.service.impl.size.ProductSizeChanger;
import com.cafe.product.service.impl.size.ProductSizeDeleter;
import com.cafe.product.service.vo.size.ProductSizeInfoUpdateFormFixture;
import com.cafe.product.service.vo.size.ProductSizePriceUpdateFormFixture;
import com.cafe.product.service.vo.size.SizeRegistrationFormFixture;
import com.cafe.product.service.vo.category.ProductCategoryRegistrationFormFixture;
import com.cafe.product.service.vo.category.ProductCategoryUpdateFormFixture;
import com.cafe.product.service.vo.cateory.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.cateory.ProductCategoryUpdateForm;
import com.cafe.product.service.vo.info.PreprocessedProductInfoRegistrationForm;
import com.cafe.product.service.vo.info.PreprocessedProductInfoRegistrationFormFixture;
import com.cafe.product.service.vo.info.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.info.ProductDetailInfoUpdateFormFixture;
import com.cafe.product.service.vo.info.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.info.ProductInfoRegistrationFormFixture;
import com.cafe.product.service.vo.info.ProductPriceInfoUpdateForm;
import com.cafe.product.service.vo.info.ProductPriceInfoUpdateFormFixture;
import com.cafe.product.service.vo.size.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.size.ProductSizePriceUpdateForm;
import com.cafe.product.service.vo.size.SizeRegistrationForm;
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
class ProductCommandServiceTest {

    @InjectMocks
    ProductCommandService productCommandService;

    @Mock
    ProductInfoPreprocessor productInfoPreprocessor;

    @Mock
    ProductCategoryDuplicationValidator productCategoryDuplicationValidator;
    @Mock
    ProductInfoDuplicationValidator productInfoDuplicationValidator;

    @Mock
    ProductInfoIntegrationCreator productInfoIntegrationCreator;
    @Mock
    ProductCategoryCreator productCategoryCreator;

    @Mock
    ProductInfoChanger productInfoChanger;
    @Mock
    ProductCategoryChanger productCategoryChanger;
    @Mock
    ProductSizeChanger productSizeChanger;

    @Mock
    ProductCategoryIntegrationDeleter productCategoryIntegrationDeleter;
    @Mock
    ProductInfoIntegrationDeleter productInfoIntegrationDeleter;
    @Mock
    ProductSizeDeleter productSizeDeleter;

    @Test
    void 상품_카테고리를_등록할_수_있다() {
        // given
        ProductCategoryRegistrationForm productCategoryRegistrationForm = ProductCategoryRegistrationFormFixture.STANDARD.newInstance();

        // when
        productCommandService.register(productCategoryRegistrationForm);

        // then
        then(productCategoryDuplicationValidator).should(times(1)).validate(productCategoryRegistrationForm.name());
        then(productCategoryCreator).should(times(1)).create(productCategoryRegistrationForm);
    }

    @Test
    void 상품을_등록할_수_있다() {
        // given
        ProductInfoRegistrationForm form = ProductInfoRegistrationFormFixture.STANDARD.newInstance();
        PreprocessedProductInfoRegistrationForm preprocessedForm = PreprocessedProductInfoRegistrationFormFixture.STANDARD.newInstance();
        List<SizeRegistrationForm> sizeRegistrationFormList = List.of(
                SizeRegistrationFormFixture.SMALL.newInstance(),
                SizeRegistrationFormFixture.LARGE.newInstance()
        );

        given(productInfoPreprocessor.preprocess(form)).willReturn(preprocessedForm);

        // when
        productCommandService.register(form, sizeRegistrationFormList);

        // then
        then(productInfoDuplicationValidator).should(times(1)).validate(form);
        then(productInfoIntegrationCreator).should(times(1)).create(preprocessedForm, sizeRegistrationFormList);
    }

    @Test
    void 상품_가격_정보를_수정할_수_있다() {
        // given
        ProductPriceInfoUpdateForm productPriceInfoUpdateForm = ProductPriceInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productCommandService.update(productPriceInfoUpdateForm);

        // then
        then(productInfoChanger).should(times(1)).change(productPriceInfoUpdateForm);
    }

    @Test
    void 상품_디테일_정보를_수정할_수_있다() {
        // given
        ProductDetailInfoUpdateForm productDetailInfoUpdateForm = ProductDetailInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productCommandService.update(productDetailInfoUpdateForm);

        // then
        then(productInfoDuplicationValidator).should(times(1)).validate(productDetailInfoUpdateForm);
        then(productInfoChanger).should(times(1)).change(productDetailInfoUpdateForm);
    }

    @Test
    void 상품_카테고리를_수정할_수_있다() {
        // given
        ProductCategoryUpdateForm productCategoryUpdateForm = ProductCategoryUpdateFormFixture.STANDARD.newInstance();

        // when
        productCommandService.update(productCategoryUpdateForm);

        // then
        then(productCategoryDuplicationValidator).should(times(1)).validate(productCategoryUpdateForm.name());
        then(productCategoryChanger).should(times(1)).change(productCategoryUpdateForm);
    }

    @Test
    void 상품_사이즈_가격을_수정할_수_있다() {
        // given
        ProductSizePriceUpdateForm productSizePriceUpdateForm = ProductSizePriceUpdateFormFixture.STANDARD.newInstance();

        // when
        productCommandService.update(productSizePriceUpdateForm);

        // then
        then(productSizeChanger).should(times(1)).change(productSizePriceUpdateForm);
    }

    @Test
    void 상품_사이즈_정보를_수정할_수_있다() {
        // given
        ProductSizeInfoUpdateForm productSizeInfoUpdateForm = ProductSizeInfoUpdateFormFixture.STANDARD.newInstance();

        // when
        productCommandService.update(productSizeInfoUpdateForm);

        // then
        then(productSizeChanger).should(times(1)).change(productSizeInfoUpdateForm);
    }

    @Test
    void 상품_사이즈를_삭제할_수_있다() {
        // given
        Long productSizeId = 1L;

        // when
        productCommandService.deleteProductSize(productSizeId);

        // then
        then(productSizeDeleter).should(times(1)).deleteByProductSizeId(productSizeId);
    }

    @Test
    void 상품_정보를_삭제할_수_있다() {
        // given
        Long productInfoId = 1L;

        // when
        productCommandService.deleteProductInfo(productInfoId);

        // then
        then(productInfoIntegrationDeleter).
                should(times(1))
                .deleteByProductInfoId(productInfoId);
    }

    @Test
    void 상품_카테고리를_삭제할_수_있다() {
        // given
        Long productCategoryId = 1L;

        // when
        productCommandService.deleteProductCategory(productCategoryId);

        // then
        then(productCategoryIntegrationDeleter)
                .should(times(1))
                .deleteByProductCategoryId(productCategoryId);
    }

}
