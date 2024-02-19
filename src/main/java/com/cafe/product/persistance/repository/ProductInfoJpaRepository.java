package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoJpaRepository extends JpaRepository<ProductInfoJpaEntity, Long> {
    boolean existsByBarcode(String barcode);

    boolean existsByBarcodeAndProductInfoIdNot(String barcode, Long productInfoId);

}
