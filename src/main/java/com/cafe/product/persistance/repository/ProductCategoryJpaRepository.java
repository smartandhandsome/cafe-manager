package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductCategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryJpaRepository extends JpaRepository<ProductCategoryJpaEntity, Long> {

    boolean existsByName(String name);

}
