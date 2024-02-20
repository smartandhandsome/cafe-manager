package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductSizeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductSizeJpaRepository extends JpaRepository<ProductSizeJpaEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_sizes WHERE product_info_id = :productInfoId", nativeQuery = true)
    void deleteAllByProductInfoId(Long productInfoId);

}
