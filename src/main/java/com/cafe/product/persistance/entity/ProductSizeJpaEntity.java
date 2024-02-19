package com.cafe.product.persistance.entity;

import com.cafe.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_sizes")
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

    @ManyToOne
    @JoinColumn(name = "product_info_id")
    private ProductInfoJpaEntity productInfoJpaEntity;

    @Builder
    private ProductSizeJpaEntity(String name, int extraCharge, int extraCost, ProductInfoJpaEntity productInfoJpaEntity) {
        this.name = name;
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
        this.productInfoJpaEntity = productInfoJpaEntity;
    }

    public String getName() {
        return name;
    }

    public int getExtraCharge() {
        return extraCharge;
    }

    public int getExtraCost() {
        return extraCost;
    }

    public Long getProductInfoId() {
        return productInfoJpaEntity.getProductInfoId();
    }

    public void changePriceInfo(int extraCharge, int extraCost) {
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
    }

    public void changeName(String name) {
        this.name = name;
    }

}
