package com.cafe.product.persistance.repository;

import com.cafe.common.model.BaseRepositoryTest;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductInfoJpaEntityFixture;
import com.cafe.product.persistance.entity.ProductSizeJpaEntity;
import com.cafe.product.persistance.entity.ProductSizeJpaEntityFixture;
import com.cafe.product.service.vo.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.ProductSizeInfoUpdateFormFixture;
import com.cafe.product.service.vo.ProductSizePriceUpdateForm;
import com.cafe.product.service.vo.ProductSizePriceUpdateFormFixture;
import com.cafe.product.service.vo.SizeRegistrationForm;
import com.cafe.product.service.vo.SizeRegistrationFormFixture;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductSizeJpaRepositoryAdapterTest extends BaseRepositoryTest {

    @InjectMocks
    private ProductSizeJpaRepositoryAdapter productSizeJpaRepositoryAdapter;
    @Mock
    private ProductSizeJdbcRepository productSizeJdbcRepository;
    @Mock
    private ProductSizeJpaRepository productSizeJpaRepository;

    @Test
    void 상품_사이즈_정보를_모두_생성할_수_있다() {
        // given
        ProductInfoJpaEntity productInfoJpaEntity = ProductInfoJpaEntityFixture.STANDARD.newInstance();
        List<SizeRegistrationForm> sizeRegistrationForms = List.of(
                SizeRegistrationFormFixture.SMALL.newInstance(),
                SizeRegistrationFormFixture.LARGE.newInstance()
        );

        // when
        productSizeJpaRepositoryAdapter.createAll(productInfoJpaEntity, sizeRegistrationForms);

        // then
        then(productSizeJdbcRepository).should().saveAll(anyList());
    }

    @Test
    void 상품_사이즈를_아이디로_삭제할_수_있다() {
        // given
        Long productSizeId = 1L;

        // when
        productSizeJpaRepositoryAdapter.deleteByProductSizeId(productSizeId);

        // then
        then(productSizeJpaRepository).should(times(1)).deleteById(productSizeId);
    }

    @Test
    void 상품_정보_아이디에_해당하는_모든_상품_사이즈를_삭제할_수_있다() {
        // given
        Long productInfoId = 1L;

        // when
        productSizeJpaRepositoryAdapter.deleteAllByProductInfoId(productInfoId);

        // then
        then(productSizeJpaRepository)
                .should(times(1))
                .deleteAllByProductInfoId(productInfoId);
    }

    @Nested
    class 상품_사이즈_정보를_수정할_수_있다 {

        @Test
        void 해당_아이디의_데이터가_존재할_때() {
            // given
            ProductSizeInfoUpdateForm productSizeInfoUpdateForm = ProductSizeInfoUpdateFormFixture.STANDARD.newInstance();
            ProductSizeJpaEntity entity = ProductSizeJpaEntityFixture.LARGE.newInstance(
                    ProductInfoJpaEntityFixture.STANDARD.newInstance()
            );

            given(productSizeJpaRepository.findById(productSizeInfoUpdateForm.productSizeId())).willReturn(Optional.of(entity));

            // when
            productSizeJpaRepositoryAdapter.change(productSizeInfoUpdateForm);

            // then
            assertThat(entity.getName()).isEqualTo(productSizeInfoUpdateForm.name());
        }

        @Test
        void 해당_아이디의_데이터가_존재하지_않을_때() {
            // given
            ProductSizeInfoUpdateForm productSizeInfoUpdateForm = ProductSizeInfoUpdateFormFixture.STANDARD.newInstance();

            given(productSizeJpaRepository.findById(productSizeInfoUpdateForm.productSizeId())).willReturn(Optional.empty());

            // when, then
            assertThatThrownBy(() -> productSizeJpaRepositoryAdapter.change(productSizeInfoUpdateForm))
                    .isExactlyInstanceOf(EntityNotFoundException.class);
        }

    }

    @Nested
    class 상품_사이즈_가격을_수정할_수_있다 {

        @Test
        void 해당_아이디의_데이터가_존재할_때() {
            // given
            ProductSizePriceUpdateForm productSizePriceUpdateForm = ProductSizePriceUpdateFormFixture.STANDARD.newInstance();
            ProductSizeJpaEntity entity = ProductSizeJpaEntityFixture.LARGE.newInstance(
                    ProductInfoJpaEntityFixture.STANDARD.newInstance()
            );

            given(productSizeJpaRepository.findById(productSizePriceUpdateForm.productSizeId())).willReturn(Optional.of(entity));

            // when
            productSizeJpaRepositoryAdapter.change(productSizePriceUpdateForm);

            // then
            assertAll(
                    () -> assertThat(entity.getExtraCharge()).isEqualTo(productSizePriceUpdateForm.extraCharge()),
                    () -> assertThat(entity.getExtraCost()).isEqualTo(productSizePriceUpdateForm.extraCost())
            );
        }

        @Test
        void 해당_아이디의_데이터가_존재하지_않을_때() {
            // given
            ProductSizePriceUpdateForm productSizePriceUpdateForm = ProductSizePriceUpdateFormFixture.STANDARD.newInstance();

            given(productSizeJpaRepository.findById(productSizePriceUpdateForm.productSizeId())).willReturn(Optional.empty());

            // when, then
            assertThatThrownBy(() -> productSizeJpaRepositoryAdapter.change(productSizePriceUpdateForm))
                    .isExactlyInstanceOf(EntityNotFoundException.class);
        }

    }

}
