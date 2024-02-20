package com.cafe.product.persistance.repository;

import com.cafe.common.model.BaseRepositoryTest;
import com.cafe.product.persistance.dto.ProductListViewDto;
import com.cafe.product.persistance.entity.ProductCategoryJpaEntity;
import com.cafe.product.persistance.entity.ProductCategoryJpaEntityFixture;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductInfoJpaEntityFixture;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductInfoRegistrationFormFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ProductInfoJdbcRepository.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductInfoJdbcRepositoryTest extends BaseRepositoryTest {

    @Autowired
    ProductInfoJdbcRepository productInfoJdbcRepository;
    @Autowired
    ProductInfoJpaRepository productInfoJpaRepository;

    @BeforeEach
    void setUp() {
        productInfoJpaRepository.saveAll(ProductInfoJpaEntityFixture.dummys());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void 상품_리스트_정보를_가져올_수_있다() {
        // given

        // when
        List<ProductListViewDto> productListViewPagination = productInfoJdbcRepository.findProductListViewPagination(1L, 10);

        // then
        assertThat(productListViewPagination).hasSize(10);
    }

}
