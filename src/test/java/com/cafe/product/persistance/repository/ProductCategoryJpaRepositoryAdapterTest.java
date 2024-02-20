package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductCategoryJpaEntity;
import com.cafe.product.persistance.entity.ProductCategoryJpaEntityFixture;
import com.cafe.product.service.vo.ProductCategoryRegistrationFormFixture;
import com.cafe.product.service.vo.ProductCategoryUpdateFormFixture;
import com.cafe.product.service.vo.cateory.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.cateory.ProductCategoryUpdateForm;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductCategoryJpaRepositoryAdapterTest {

    @InjectMocks
    ProductCategoryJpaRepositoryAdapter productCategoryJpaRepositoryAdapter;
    @Mock
    ProductCategoryJpaRepository productCategoryJpaRepository;

    @Test
    void 상품_카테고리를_생성할_수_있다() {
        // given
        ProductCategoryRegistrationForm productCategoryRegistrationForm = ProductCategoryRegistrationFormFixture.STANDARD.newInstance();

        // when
        productCategoryJpaRepositoryAdapter.create(productCategoryRegistrationForm);

        // then
        then(productCategoryJpaRepository).should(times(1)).save(any(ProductCategoryJpaEntity.class));
    }

    @Test
    void 상품_카테고리_아이디에_해당하는_카테고리를_삭제할_수_있다() {
        // given
        Long productCategoryId = 1L;

        // when
        productCategoryJpaRepositoryAdapter.deleteByProductCategoryId(productCategoryId);

        // then
        then(productCategoryJpaRepository).should(times(1)).deleteById(productCategoryId);
    }

    @Nested
    class 카테고리_이름이_존재하는지_확인할_수_있다 {

        @Test
        void 이름이_존재할_때() {
            // given
            String name = "existedName";
            given(productCategoryJpaRepository.existsByName(name)).willReturn(true);

            // when
            boolean isExisted = productCategoryJpaRepositoryAdapter.existsByName(name);

            // then
            assertThat(isExisted).isTrue();
        }

        @Test
        void 이름이_존재하지_않을_때() {
            // given
            String name = "notExistedName";
            given(productCategoryJpaRepository.existsByName(name)).willReturn(false);

            // when
            boolean isExisted = productCategoryJpaRepositoryAdapter.existsByName(name);

            // then
            assertThat(isExisted).isFalse();
        }

    }

    @Nested
    class 상품_카테고리를_수정할_수_있다 {

        @Test
        void 데이터_존재할_때() {
            // given
            ProductCategoryJpaEntity entity = ProductCategoryJpaEntityFixture.STANDARD.newInstance();
            ProductCategoryUpdateForm productCategoryUpdateForm
                    = ProductCategoryUpdateFormFixture.STANDARD.newInstance();

            given(productCategoryJpaRepository.findById(productCategoryUpdateForm.productCategoryId()))
                    .willReturn(Optional.of(entity));

            // when
            productCategoryJpaRepositoryAdapter.change(productCategoryUpdateForm);

            // then
            assertAll(
                    () -> assertThat(entity.getName()).isEqualTo(productCategoryUpdateForm.name())
            );
        }

        @Test
        void 데이터_존재하지_않을_때() {
            // given
            ProductCategoryUpdateForm productCategoryUpdateForm
                    = ProductCategoryUpdateFormFixture.STANDARD.newInstance();

            given(productCategoryJpaRepository.findById(productCategoryUpdateForm.productCategoryId()))
                    .willReturn(Optional.empty());

            // when, then
            assertThatThrownBy(() -> productCategoryJpaRepositoryAdapter.change(productCategoryUpdateForm))
                    .isExactlyInstanceOf(EntityNotFoundException.class);
        }

    }

}
