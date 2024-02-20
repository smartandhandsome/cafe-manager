package com.cafe.product.persistance.entity;

import com.cafe.common.util.EntityIdInjector;

import java.util.ArrayList;
import java.util.List;

public enum ProductInfoJpaEntityFixture {
    STANDARD(
            Constants.ID,
            Constants.NAME,
            Constants.DESCRIPTION,
            Constants.BARCODE,
            Constants.ZERO,
            Constants.ZERO,
            Constants.EXPIRATION_DURATION,
            Constants.ID
    ),
    NOT_EXISTED(
            Constants.NOT_EXISTED_ID,
            Constants.NAME,
            Constants.DESCRIPTION,
            Constants.NOT_EXISTED_BARCODE,
            Constants.ZERO,
            Constants.ZERO,
            Constants.EXPIRATION_DURATION,
            Constants.ID
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

    public ProductInfoJpaEntity newInstance(String barcode) {
        ProductInfoJpaEntity entity = ProductInfoJpaEntity.builder()
                .name(name)
                .description(description)
                .barcode(barcode)
                .basePrice(basePrice)
                .baseCost(baseCost)
                .expirationDuration(expirationDuration)
                .productCategoryId(productCategoryId)
                .build();
        return entity;
    }

    public static List<ProductInfoJpaEntity> dummys() {
        List<ProductInfoJpaEntity> dummys = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            dummys.add(
                    STANDARD.newInstance(String.valueOf(i))
            );
        }
        return dummys;
    }

    private final static class Constants {
        private static final String NAME = "name";
        private static final String DESCRIPTION = "description";
        private static final String BARCODE = "barcode";
        private static final int ZERO = 0;
        private static final Long ID = 1L;
        private static final String EXPIRATION_DURATION = "7일";

        private static final Long NOT_EXISTED_ID = 999999999999L;
        private static final String NOT_EXISTED_BARCODE = "notExistedBarcode";
    }

}
