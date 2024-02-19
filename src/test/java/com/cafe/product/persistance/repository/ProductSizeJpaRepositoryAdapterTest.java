package com.cafe.product.persistance.repository;

import com.cafe.common.model.BaseRepositoryTest;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductInfoJpaEntityFixture;
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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductSizeJpaRepositoryAdapterTest extends BaseRepositoryTest {

    @InjectMocks
    private ProductSizeJpaRepositoryAdapter productSizeJpaRepositoryAdapter;
    @Mock
    private ProductSizeJdbcRepository productSizeJdbcRepository;

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

}
