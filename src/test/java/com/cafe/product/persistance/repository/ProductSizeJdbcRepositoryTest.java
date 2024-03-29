package com.cafe.product.persistance.repository;

import com.cafe.common.model.BaseRepositoryTest;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductInfoJpaEntityFixture;
import com.cafe.product.persistance.entity.ProductSizeJpaEntity;
import com.cafe.product.persistance.entity.ProductSizeJpaEntityFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
@Import(ProductSizeJdbcRepository.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductSizeJdbcRepositoryTest extends BaseRepositoryTest {

    @Autowired
    ProductSizeJdbcRepository productSizeJdbcRepository;
    @Autowired
    ProductInfoJpaRepository productInfoJpaRepository;

    ProductInfoJpaEntity productInfoJpaEntity;

    @BeforeEach
    void setUp() {
        productInfoJpaEntity = productInfoJpaRepository.save(ProductInfoJpaEntityFixture.STANDARD.newInstance());
        System.out.println(productInfoJpaEntity.getCreatedDateTime());
        System.out.println(productInfoJpaEntity.getLastModifiedDateTime());
    }

    @Test
    void 상품_사이즈를_배치_삽입할_수_있다() {
        // given
        Long productInfoId = 1L;
        List<ProductSizeJpaEntity> productSizeJpaEntities = List.of(
                ProductSizeJpaEntityFixture.SMALL.newInstance(productInfoId),
                ProductSizeJpaEntityFixture.LARGE.newInstance(productInfoId)
        );

        // when, then
        assertThatCode(() ->
                productSizeJdbcRepository.saveAll(productSizeJpaEntities)
        ).doesNotThrowAnyException();
    }

}
