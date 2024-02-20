package com.cafe.product.persistance.entity;

import com.cafe.common.util.EntityIdInjector;

public enum ProductSizeJpaEntityFixture {
    SMALL(
            Constants.ONE,
            Constants.SMALL,
            Constants.ZERO,
            Constants.ZERO
    ),
    LARGE(
            Constants.TWO,
            Constants.LARGE,
            Constants.ZERO,
            Constants.ZERO
    ),
    ;

    private final Long productSizeId;
    private final String name;
    private final int extraCharge;
    private final int extraCost;

    ProductSizeJpaEntityFixture(Long productSizeId, String name, int extraCharge, int extraCost) {
        this.productSizeId = productSizeId;
        this.name = name;
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
    }

    public ProductSizeJpaEntity newInstance(Long productInfoId) {
        ProductSizeJpaEntity entity = ProductSizeJpaEntity.builder()
                .name(name)
                .extraCharge(extraCharge)
                .extraCost(extraCost)
                .productInfoId(productInfoId)
                .build();

        EntityIdInjector.inject(entity, "productSizeId", productSizeId);
        return entity;
    }

    private static final class Constants {
        private static final String SMALL = "small";
        private static final String LARGE = "large";
        private static final int ZERO = 0;
        private static final Long ONE = 1L;
        private static final Long TWO = 2L;
    }
}
