package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductCategoryJpaEntity;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductCategoryRegistrationFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
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

}
