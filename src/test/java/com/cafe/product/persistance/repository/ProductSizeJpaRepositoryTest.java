package com.cafe.product.persistance.repository;

import com.cafe.common.model.BaseRepositoryTest;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductInfoJpaEntityFixture;
import com.cafe.product.persistance.entity.ProductSizeJpaEntity;
import com.cafe.product.persistance.entity.ProductSizeJpaEntityFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ProductSizeJpaRepositoryTest extends BaseRepositoryTest {

    @Autowired
    ProductSizeJpaRepository productSizeJpaRepository;

    @Autowired
    ProductInfoJpaRepository productInfoJpaRepository;

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void 상품_정보_아이디에_해당하는_상품_사이즈를_모두_삭제할_수_있다() {
        // given
        ProductInfoJpaEntity info = ProductInfoJpaEntityFixture.STANDARD.newInstance();
        productInfoJpaRepository.save(info);

        ProductSizeJpaEntity small = ProductSizeJpaEntityFixture.SMALL.newInstance(info.getProductInfoId());
        ProductSizeJpaEntity large = ProductSizeJpaEntityFixture.LARGE.newInstance(info.getProductInfoId());
        productSizeJpaRepository.saveAll(List.of(small, large));

        // when
        productSizeJpaRepository.deleteAllByProductInfoId(info.getProductInfoId());

        // then
        Optional<ProductSizeJpaEntity> smallById = productSizeJpaRepository.findById(small.getProductSizeId());
        Optional<ProductSizeJpaEntity> largeById = productSizeJpaRepository.findById(large.getProductSizeId());
        assertAll(
                () -> assertThat(smallById).isNotPresent(),
                () -> assertThat(largeById).isNotPresent()
        );
    }

}
