package com.cafe.product.persistance.repository;

import com.cafe.common.model.BaseRepositoryTest;
import com.cafe.product.persistance.entity.ProductCategoryJpaEntity;
import com.cafe.product.persistance.entity.ProductCategoryJpaEntityFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductCategoryJpaRepositoryTest extends BaseRepositoryTest {

    @Autowired
    ProductCategoryJpaRepository ProductCategoryJpaRepository;

    ProductCategoryJpaEntity entity;
    ProductCategoryJpaEntity notExistedEntity;

    @BeforeEach
    void setUp() {
        notExistedEntity = ProductCategoryJpaEntityFixture.NOT_EXISTED.newInstance();
        entity = ProductCategoryJpaEntityFixture.STANDARD.newInstance();
        ProductCategoryJpaRepository.save(entity);
    }

    @Nested
    class 해당_카테고리_이름이_존재하는지_확인할_수_있다 {

        @Test
        void 동일한_카테고리_이름_존재할_때() {
            // given
            String name = entity.getName();

            // when
            boolean isExisted = ProductCategoryJpaRepository.existsByName(name);

            // then
            assertThat(isExisted).isTrue();
        }

        @Test
        void 동일한_카테고리_이름_존재하지_않을_때() {
            // given
            String name = notExistedEntity.getName();

            // when
            boolean isExisted = ProductCategoryJpaRepository.existsByName(name);

            // then
            assertThat(isExisted).isFalse();
        }

    }

}
