package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductInfoJpaRepository extends JpaRepository<ProductInfoJpaEntity, Long> {
    boolean existsByBarcode(String barcode);

    boolean existsByBarcodeAndProductInfoIdNot(String barcode, Long productInfoId);

    @Transactional(readOnly = true)
    @Query(value = "SELECT p.product_info_id FROM product_info p WHERE p.product_category_id = :productCategoryId", nativeQuery = true)
    List<Long> findAllByProductCategoryId(Long productCategoryId);
}
