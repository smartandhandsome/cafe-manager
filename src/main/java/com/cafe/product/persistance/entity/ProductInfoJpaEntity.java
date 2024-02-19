package com.cafe.product.persistance.entity;

import com.cafe.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product_Infos", indexes = {
        @Index(name = "product_Infos_category_index", columnList = "product_category_id")
})
@NoArgsConstructor
public class ProductInfoJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productInfoId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String barcode;

    @Column(nullable = false)
    private int basePrice;

    @Column(nullable = false)
    private int baseCost;

    @Column(nullable = false)
    private String expirationDuration;

    @Column(nullable = false)
    private Long productCategoryId;

    @Builder
    private ProductInfoJpaEntity(String name, String description, String barcode, int basePrice, int baseCost, String expirationDuration, Long productCategoryId) {
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.basePrice = basePrice;
        this.baseCost = baseCost;
        this.expirationDuration = expirationDuration;
        this.productCategoryId = productCategoryId;
    }

    public void changePriceInfo(int basePrice, int baseCost) {
        this.basePrice = basePrice;
        this.baseCost = baseCost;
    }

}
