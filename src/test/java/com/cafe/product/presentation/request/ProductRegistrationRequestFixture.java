package com.cafe.product.presentation.request;

import java.util.List;

public enum ProductRegistrationRequestFixture {
    STANDARD(
            Constants.ID,
            Constants.ZERO,
            Constants.ZERO,
            Constants.name,
            Constants.description,
            Constants.barcode,
            Constants.expirationDuration
    ),
    ;

    private final Long productCategoryId;
    private final int basePrice;
    private final int baseCost;
    private final String name;
    private final String description;
    private final String barcode;
    private final String expirationDuration;

    ProductRegistrationRequestFixture(Long productCategoryId, int basePrice, int baseCost, String name, String description, String barcode, String expirationDuration) {
        this.productCategoryId = productCategoryId;
        this.basePrice = basePrice;
        this.baseCost = baseCost;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.expirationDuration = expirationDuration;
    }

    public ProductRegistrationRequest newInstance() {
        return new ProductRegistrationRequest(
                productCategoryId,
                basePrice,
                baseCost,
                name,
                description,
                barcode,
                expirationDuration,
                List.of(
                        SizeRegistrationRequestFixture.SMALL.newInstance(),
                        SizeRegistrationRequestFixture.LARGE.newInstance()
                )
        );
    }

    private static final class Constants {
        private static final Long ID = 1L;
        private static final String name = "name";
        private static final String description = "description";
        private static final String barcode = "barcode";
        private static final String expirationDuration = "expirationDuration";
        private static final int ZERO = 0;
    }
}
