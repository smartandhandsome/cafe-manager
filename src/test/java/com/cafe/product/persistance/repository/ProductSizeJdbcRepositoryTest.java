package com.cafe.product.persistance.repository;

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
class ProductSizeJdbcRepositoryTest {

    @Autowired
    ProductSizeJdbcRepository productSizeJdbcRepository;
    @Autowired
    ProductInfoJpaRepository productInfoJpaRepository;

    ProductInfoJpaEntity productInfoJpaEntity;

    @BeforeEach
    void setUp() {
        productInfoJpaEntity = ProductInfoJpaEntityFixture.STANDARD.newInstance();
        productInfoJpaRepository.save(productInfoJpaEntity);
    }

    @Test
    void 상품_사이즈를_배치_삽입할_수_있다() {
        // given
        List<ProductSizeJpaEntity> productSizeJpaEntities = List.of(
                ProductSizeJpaEntityFixture.SMALL.newInstance(productInfoJpaEntity),
                ProductSizeJpaEntityFixture.LARGE.newInstance(productInfoJpaEntity)
        );

        // when, then
        assertThatCode(() ->
                productSizeJdbcRepository.saveAll(productSizeJpaEntities)
        ).doesNotThrowAnyException();
    }

}
