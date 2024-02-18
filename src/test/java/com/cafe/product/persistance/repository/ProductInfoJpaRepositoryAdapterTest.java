package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductInfoRegistrationFormFixture;
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
class ProductInfoJpaRepositoryAdapterTest {

    @InjectMocks
    ProductInfoJpaRepositoryAdapter productInfoJpaRepositoryAdapter;
    @Mock
    ProductInfoJpaRepository productInfoJpaRepository;

    @Test
    void 상품_정보를_생성할_수_있다() {
        // given
        ProductInfoRegistrationForm productInfoRegistrationForm = ProductInfoRegistrationFormFixture.STANDARD.newInstance();

        // when
        productInfoJpaRepositoryAdapter.create(productInfoRegistrationForm);

        // then
        then(productInfoJpaRepository).should(times(1)).save(any(ProductInfoJpaEntity.class));
    }

}
