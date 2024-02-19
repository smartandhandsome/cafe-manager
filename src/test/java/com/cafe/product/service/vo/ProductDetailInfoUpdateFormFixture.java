package com.cafe.product.service.vo;

public enum ProductDetailInfoUpdateFormFixture {
    STANDARD(
            Constants.ID,
            Constants.NAME,
            Constants.DESCRIPTION,
            Constants.BARCODE,
            Constants.EXPIRATION_DURATION
    ),
    ;

    private final Long productCategoryId;
    private final String name;
    private final String description;
    private final String barcode;
    private final String expirationDuration;

    ProductDetailInfoUpdateFormFixture(Long productCategoryId, String name, String description, String barcode, String expirationDuration) {
        this.productCategoryId = productCategoryId;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.expirationDuration = expirationDuration;
    }

    public ProductDetailInfoUpdateForm newInstance() {
        return new ProductDetailInfoUpdateForm(
                productCategoryId,
                name,
                description,
                barcode,
                expirationDuration
        );
    }

    private static final class Constants {
        private final static Long ID = 1L;
        private final static String NAME = "name";
        private final static String DESCRIPTION = "description";
        private final static String BARCODE = "barcode";
        private final static String EXPIRATION_DURATION = "expirationDuration";
    }

}
