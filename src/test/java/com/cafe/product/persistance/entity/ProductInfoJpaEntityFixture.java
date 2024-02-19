package com.cafe.product.persistance.entity;

import com.cafe.common.util.EntityIdInjector;

import java.time.Duration;

public enum ProductInfoJpaEntityFixture {
    STANDARD(
            Constants.ONE,
            Constants.NAME,
            Constants.DESCRIPTION,
            Constants.BARCODE,
            Constants.ZERO,
            Constants.ZERO,
            Constants.EXPIRATION_DURATION,
            Constants.ONE
    ),
    NOT_EXISTED(
            Constants.ONE,
            Constants.NAME,
            Constants.DESCRIPTION,
            Constants.NOT_EXISTED_BARCODE,
            Constants.ZERO,
            Constants.ZERO,
            Constants.EXPIRATION_DURATION,
            Constants.ONE
    ),
    ;

    private final Long productInfoId;
    private final String name;
    private final String description;
    private final String barcode;
    private final int basePrice;
    private final int baseCost;
    private final String expirationDuration;
    private final Long productCategoryId;

    ProductInfoJpaEntityFixture(Long productInfoId, String name, String description, String barcode, int basePrice, int baseCost, String expirationDuration, Long productCategoryId) {
        this.productInfoId = productInfoId;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.basePrice = basePrice;
        this.baseCost = baseCost;
        this.expirationDuration = expirationDuration;
        this.productCategoryId = productCategoryId;
    }

    public ProductInfoJpaEntity newInstance() {
        ProductInfoJpaEntity entity = ProductInfoJpaEntity.builder()
                .name(name)
                .description(description)
                .barcode(barcode)
                .basePrice(basePrice)
                .baseCost(baseCost)
                .expirationDuration(expirationDuration)
                .productCategoryId(productCategoryId)
                .build();
        EntityIdInjector.inject(entity, "productInfoId", productInfoId);
        return entity;
    }

    private final static class Constants {
        private static final String NAME = "name";
        private static final String DESCRIPTION = "description";
        private static final String BARCODE = "barcode";
        private static final int ZERO = 0;
        private static final Long ONE = 1L;
        private static final String EXPIRATION_DURATION = "7일";

        private static final String NOT_EXISTED_BARCODE = "notExistedBarcode";
    }

}
