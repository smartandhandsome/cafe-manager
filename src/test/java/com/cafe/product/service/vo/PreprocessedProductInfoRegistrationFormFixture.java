package com.cafe.product.service.vo;

import com.cafe.product.service.vo.info.PreprocessedProductInfoRegistrationForm;

public enum PreprocessedProductInfoRegistrationFormFixture {
    STANDARD(
            Constants.ID,
            Constants.ZERO,
            Constants.ZERO,
            Constants.NAME,
            Constants.CHOSUNG,
            Constants.DESCRIPTION,
            Constants.BARCODE,
            Constants.EXPIRATION_DURATION
    ),
    ;

    private final Long productCategoryId;
    private final int basePrice;
    private final int baseCost;
    private final String name;
    private final String nameChosung;
    private final String description;
    private final String barcode;
    private final String expirationDuration;

    PreprocessedProductInfoRegistrationFormFixture(Long productCategoryId, int basePrice, int baseCost, String name, String nameChosung, String description, String barcode, String expirationDuration) {
        this.productCategoryId = productCategoryId;
        this.basePrice = basePrice;
        this.baseCost = baseCost;
        this.name = name;
        this.nameChosung = nameChosung;
        this.description = description;
        this.barcode = barcode;
        this.expirationDuration = expirationDuration;
    }

    public PreprocessedProductInfoRegistrationForm newInstance() {
        return new PreprocessedProductInfoRegistrationForm(
                productCategoryId,
                basePrice,
                baseCost,
                name,
                nameChosung,
                description,
                barcode,
                expirationDuration
        );
    }

    private static final class Constants {
        private final static Long ID = 1L;
        private final static int ZERO = 0;
        private final static String NAME = "name";
        private final static String CHOSUNG = "chosung";
        private final static String DESCRIPTION = "description";
        private final static String BARCODE = "barcode";
        private final static String EXPIRATION_DURATION = "expirationDuration";
    }

}
