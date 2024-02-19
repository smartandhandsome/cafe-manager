package com.cafe.product.persistance.repository;

import com.cafe.common.model.BaseRepositoryTest;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductInfoJpaEntityFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductInfoJpaRepositoryTest extends BaseRepositoryTest {

    @Autowired
    ProductInfoJpaRepository productInfoJpaRepository;

    ProductInfoJpaEntity entity;
    ProductInfoJpaEntity notExistedEntity;

    @BeforeEach
    void setUp() {
        notExistedEntity = ProductInfoJpaEntityFixture.NOT_EXISTED.newInstance();
        entity= ProductInfoJpaEntityFixture.STANDARD.newInstance();
        productInfoJpaRepository.save(entity);
    }

    @Nested
    class 해당_바코드가_존재하는지_확인할_수_있다 {

        @Test
        void 동일한_바코드_존재할_때() {
            // given
            String barcode = entity.getBarcode();

            // when
            boolean isExisted = productInfoJpaRepository.existsByBarcode(barcode);

            // then
            assertThat(isExisted).isTrue();
        }

        @Test
        void 동일한_바코드_존재하지_않을_때() {
            // given
            String barcode = notExistedEntity.getBarcode();

            // when
            boolean isExisted = productInfoJpaRepository.existsByBarcode(barcode);

            // then
            assertThat(isExisted).isFalse();
        }

    }

    @Nested
    class 특정_아이디를_제외한_상품_바코드_존재하는지_확인할_수_있다 {


        @Test
        void 특정_아이디를_제외하고_동일한_바코드_존재할_때() {
            // given
            String barcode = entity.getBarcode();
            Long anotherProductInfoId = notExistedEntity.getProductInfoId();

            // when
            boolean isExisted = productInfoJpaRepository.existsByBarcodeAndProductInfoIdNot(barcode, anotherProductInfoId);

            // then
            assertThat(isExisted).isTrue();
        }

        @Test
        void 특정_아이디를_제외하고_동일한_바코드_존재하지_않을_때() {
            // given
            String barcode = entity.getBarcode();
            Long productInfoId = entity.getProductInfoId();

            // when
            boolean isExisted = productInfoJpaRepository.existsByBarcodeAndProductInfoIdNot(barcode, productInfoId);

            // then
            assertThat(isExisted).isFalse();
        }

    }

}
