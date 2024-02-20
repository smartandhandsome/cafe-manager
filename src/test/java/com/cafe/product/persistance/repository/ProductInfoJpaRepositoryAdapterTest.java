package com.cafe.product.persistance.repository;

import com.cafe.common.model.BaseRepositoryTest;
import com.cafe.product.persistance.dto.ProductListViewDto;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductInfoJpaEntityFixture;
import com.cafe.product.service.vo.PreprocessedProductInfoRegistrationFormFixture;
import com.cafe.product.service.vo.ProductDetailInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductPriceInfoUpdateFormFixture;
import com.cafe.product.service.vo.info.PreprocessedProductInfoRegistrationForm;
import com.cafe.product.service.vo.info.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.info.ProductPriceInfoUpdateForm;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductInfoJpaRepositoryAdapterTest extends BaseRepositoryTest {

    @InjectMocks
    ProductInfoJpaRepositoryAdapter productInfoJpaRepositoryAdapter;
    @Mock
    ProductInfoJpaRepository productInfoJpaRepository;
    @Mock
    ProductInfoJdbcRepository productInfoJdbcRepository;

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
        Long productInfoId = 1L;
        PreprocessedProductInfoRegistrationForm form = PreprocessedProductInfoRegistrationFormFixture.STANDARD.newInstance();
        ProductInfoJpaEntity entity = mock();

        given(productInfoJpaRepository.save(any(ProductInfoJpaEntity.class))).willReturn(entity);
        given(entity.getProductInfoId()).willReturn(productInfoId);

        // when
        productInfoJpaRepositoryAdapter.create(form);

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

    @Test
    void 상품_카테고리_아이디를_갖고_있는_모든_상품_정보를_가져올_수_있다() {
        // given
        Long productCategoryId = 1L;
        List<Long> productInfoIds = List.of(1L, 2L, 3L);

        given(productInfoJpaRepository.findAllByProductCategoryId(productCategoryId)).willReturn(productInfoIds);

        // when
        List<Long> foundProductInfoIds = productInfoJpaRepositoryAdapter.readAllProductInfoIdByProductCategoryId(productCategoryId);

        // then
        assertThat(foundProductInfoIds)
                .usingRecursiveComparison()
                .isEqualTo(productInfoIds);
    }

    @Test
    void 상품_리스트_형태를_페이지_사이즈만큼_가져올_수_있다() {
        // given
        Long productListCursorId = 1L;
        int pageSize = 10;

        // when
        List<ProductListViewDto> productListViewDtos = productInfoJpaRepositoryAdapter.readProductListViewPagination(productListCursorId, pageSize);

        // then
        then(productInfoJdbcRepository).should().findProductListViewPagination(productListCursorId, pageSize);
    }

    @Test
    void 해당_아이디보다_더_큰_아이디가_있는지_확인할_수_있다() {
        // given
        Long productInfoId = 1L;

        // when
        productInfoJpaRepositoryAdapter.hasProductInfoIdGreaterThan(productInfoId);

        // then
        then(productInfoJpaRepository).should(times(1)).existsByProductInfoIdGreaterThan(productInfoId);
    }

}
