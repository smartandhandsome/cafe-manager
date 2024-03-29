package com.cafe.product.persistance.entity;

import com.cafe.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product_sizes", indexes = {
        @Index(name = "product_size_product_info_id", columnList = "productInfoId")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSizeJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSizeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int extraCharge;

    @Column(nullable = false)
    private int extraCost;

    private Long productInfoId;

    @Builder
    private ProductSizeJpaEntity(String name, int extraCharge, int extraCost, Long productInfoId) {
        this.name = name;
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
        this.productInfoId = productInfoId;
    }

    public void changePriceInfo(int extraCharge, int extraCost) {
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
    }

    public void changeName(String name) {
        this.name = name;
    }

}
