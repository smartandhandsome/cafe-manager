package com.cafe.product.persistance.repository;

import com.cafe.common.model.BaseRepositoryTest;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductInfoJpaEntityFixture;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductDetailInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductInfoRegistrationFormFixture;
import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import com.cafe.product.service.vo.ProductPriceInfoUpdateFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductInfoJpaRepositoryAdapterTest extends BaseRepositoryTest {

    @InjectMocks
    ProductInfoJpaRepositoryAdapter productInfoJpaRepositoryAdapter;
    @Mock
    ProductInfoJpaRepository productInfoJpaRepository;

    @Test
    void 상품_바코드가_존재하는지_확인할_수_있다() {
        // given
        String barcode = "barcode";

        // when
        productInfoJpaRepositoryAdapter.existsByBarcode(barcode);

        // then
        then(productInfoJpaRepository).should(times(1)).existsByBarcode(barcode);
    }

    @Test
    void 상품_정보를_생성할_수_있다() {
        // given
        ProductInfoRegistrationForm productInfoRegistrationForm = ProductInfoRegistrationFormFixture.STANDARD.newInstance();

        // when
        productInfoJpaRepositoryAdapter.create(productInfoRegistrationForm);

        // then
        then(productInfoJpaRepository).should(times(1)).save(any(ProductInfoJpaEntity.class));
    }

    @Test
    void 특정_아이디를_제외한_상품_바코드가_존재하는지_확인할_수_있다() {
        // given
        String barcode = "barcode";
        Long productInfoId = 100L;

        // when
        productInfoJpaRepositoryAdapter.existsByBarcodeProductInfoIdNot(barcode, productInfoId);

        // then
        then(productInfoJpaRepository).should(times(1)).existsByBarcodeAndProductInfoIdNot(barcode, productInfoId);
    }

    @Test
    void 상품_가격_정보를_변경할_수_있다() {
        // given
        ProductPriceInfoUpdateForm productPriceInfoUpdateForm = ProductPriceInfoUpdateFormFixture.STANDARD.newInstance();
        ProductInfoJpaEntity entity = ProductInfoJpaEntityFixture.STANDARD.newInstance();

        given(productInfoJpaRepository.findById(productPriceInfoUpdateForm.productInfoId()))
                .willReturn(Optional.ofNullable(entity));

        // when
        productInfoJpaRepositoryAdapter.change(productPriceInfoUpdateForm);

        // then
        assertAll(
                () -> assertThat(Objects.requireNonNull(entity).getBasePrice())
                        .isEqualTo(productPriceInfoUpdateForm.basePrice()),
                () -> assertThat(Objects.requireNonNull(entity).getBaseCost())
                        .isEqualTo(productPriceInfoUpdateForm.baseCost())
        );
    }

    @Test
    void 상품_디테일_정보를_변경할_수_있다() {
        // given
        ProductDetailInfoUpdateForm productDetailInfoUpdateForm = ProductDetailInfoUpdateFormFixture.STANDARD.newInstance();
        ProductInfoJpaEntity entity = ProductInfoJpaEntityFixture.STANDARD.newInstance();

        given(productInfoJpaRepository.findById(productDetailInfoUpdateForm.productInfoId()))
                .willReturn(Optional.ofNullable(entity));

        // when
        productInfoJpaRepositoryAdapter.change(productDetailInfoUpdateForm);

        // then
        assertAll(
                () -> assertThat(Objects.requireNonNull(entity).getName())
                        .isEqualTo(productDetailInfoUpdateForm.name()),
                () -> assertThat(Objects.requireNonNull(entity).getDescription())
                        .isEqualTo(productDetailInfoUpdateForm.description()),
                () -> assertThat(Objects.requireNonNull(entity).getBarcode())
                        .isEqualTo(productDetailInfoUpdateForm.barcode()),
                () -> assertThat(Objects.requireNonNull(entity).getExpirationDuration())
                        .isEqualTo(productDetailInfoUpdateForm.expirationDuration())
        );
    }

}
