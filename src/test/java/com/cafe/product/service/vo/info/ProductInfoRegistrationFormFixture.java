package com.cafe.product.service.vo.info;

public enum ProductInfoRegistrationFormFixture {
    STANDARD(
            Constants.ID,
            Constants.ZERO,
            Constants.ZERO,
            Constants.NAME,
            Constants.DESCRIPTION,
            Constants.BARCODE,
            Constants.EXPIRATION_DURATION
    ),
    ;

    private final Long productCategoryId;
    private final int basePrice;
    private final int baseCost;
    private final String name;
    private final String description;
    private final String barcode;
    private final String expirationDuration;

    ProductInfoRegistrationFormFixture(Long productCategoryId, int basePrice, int baseCost, String name, String description, String barcode, String expirationDuration) {
        this.productCategoryId = productCategoryId;
        this.basePrice = basePrice;
        this.baseCost = baseCost;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.expirationDuration = expirationDuration;
    }

    public ProductInfoRegistrationForm newInstance() {
        return new ProductInfoRegistrationForm(
                productCategoryId,
                basePrice,
                baseCost,
                name,
                description,
                barcode,
                expirationDuration
        );
    }

    private static final class Constants {
        private final static Long ID = 1L;
        private final static int ZERO = 0;
        private final static String NAME = "name";
        private final static String DESCRIPTION = "description";
        private final static String BARCODE = "barcode";
        private final static String EXPIRATION_DURATION = "expirationDuration";
    }

}
