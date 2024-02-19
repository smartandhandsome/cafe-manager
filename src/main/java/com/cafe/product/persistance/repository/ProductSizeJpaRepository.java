package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductSizeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeJpaRepository extends JpaRepository<ProductSizeJpaEntity, Long> {
}
