package com.cafe.product.service.impl;

import com.cafe.common.exception.DuplicatedResourceException;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductDetailInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductInfoRegistrationFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductInfoDuplicationValidatorTest {

    @InjectMocks
    ProductInfoDuplicationValidator productInfoDuplicationValidator;
    @Mock
    ProductInfoReader productInfoReader;

    @Nested
    class 상품_정보_중복을_검증할_수_있다 {

        @Test
        void 중복_데이터_없을_때() {
            // given
            ProductInfoRegistrationForm productInfoRegistrationForm = ProductInfoRegistrationFormFixture.STANDARD.newInstance();

            given(productInfoReader.existsByBarcode(productInfoRegistrationForm.barcode())).willReturn(false);

            // when, then
            assertThatCode(
                    () -> productInfoDuplicationValidator.validate(productInfoRegistrationForm)
            ).doesNotThrowAnyException();
        }

        @Test
        void 중복_데이터_있을_때() {
            // given
            ProductInfoRegistrationForm productInfoRegistrationForm = ProductInfoRegistrationFormFixture.STANDARD.newInstance();

            given(productInfoReader.existsByBarcode(productInfoRegistrationForm.barcode())).willReturn(true);

            // when, then
            assertThatThrownBy(
                    () -> productInfoDuplicationValidator.validate(productInfoRegistrationForm)
            ).isExactlyInstanceOf(DuplicatedResourceException.class);
        }

    }

    @Nested
    class 특정_아이디_제외한_상품_정보_중복을_검증할_수_있다 {

        @Test
        void 중복_데이터_없을_때() {
            // given
            ProductDetailInfoUpdateForm productDetailInfoUpdateForm = ProductDetailInfoUpdateFormFixture.STANDARD.newInstance();

            given(productInfoReader.existsByBarcodeProductInfoIdNot(productDetailInfoUpdateForm.barcode(),productDetailInfoUpdateForm.productInfoId())).willReturn(false);

            // when, then
            assertThatCode(
                    () -> productInfoDuplicationValidator.validate(productDetailInfoUpdateForm)
            ).doesNotThrowAnyException();
        }

        @Test
        void 중복_데이터_있을_때() {
            // given
            ProductDetailInfoUpdateForm productDetailInfoUpdateForm = ProductDetailInfoUpdateFormFixture.STANDARD.newInstance();

            given(productInfoReader.existsByBarcodeProductInfoIdNot(productDetailInfoUpdateForm.barcode(),productDetailInfoUpdateForm.productInfoId())).willReturn(true);

            // when, then
            assertThatThrownBy(
                    () -> productInfoDuplicationValidator.validate(productDetailInfoUpdateForm)
            ).isExactlyInstanceOf(DuplicatedResourceException.class);
        }

    }

}
