package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.dto.ProductInfoCategoryDetailViewDto;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductInfoJpaRepository extends JpaRepository<ProductInfoJpaEntity, Long> {
    boolean existsByBarcode(String barcode);

    boolean existsByBarcodeAndProductInfoIdNot(String barcode, Long productInfoId);

    @Query(value = """
            SELECT p.productInfoId
            FROM ProductInfoJpaEntity p
            WHERE p.productCategoryId = :productCategoryId
            """)
    List<Long> findAllByProductCategoryId(Long productCategoryId);

    boolean existsByProductInfoIdGreaterThan(Long productInfoId);

    @Query(value = """
            SELECT new com.cafe.product.persistance.dto.ProductInfoCategoryDetailViewDto(
                i.productInfoId,
                c.productCategoryId,
                c.name,
                i.name,
                i.basePrice,
                i.baseCost,
                i.description,
                i.barcode,
                i.expirationDuration
            )
            FROM ProductInfoJpaEntity i
            JOIN ProductCategoryJpaEntity c
            ON i.productCategoryId = c.productCategoryId
            WHERE i.productInfoId = :productInfoId
            """)
    Optional<ProductInfoCategoryDetailViewDto> findProductDetail(Long productInfoId);

    List<ProductInfoJpaEntity> findByNameContaining(String name);


}
