package com.cafe.product.presentation.request;

public enum ProductDetailInfoUpdateRequestFixture {
    STANDARD(
            Constants.NAME,
            Constants.DESCRIPTION,
            Constants.BARCODE,
            Constants.EXPIRATION_DURATION
    ),
    ;

    private final String name;
    private final String description;
    private final String barcode;
    private final String expirationDuration;

    ProductDetailInfoUpdateRequestFixture(String name, String description, String barcode, String expirationDuration) {
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.expirationDuration = expirationDuration;
    }

    public ProductDetailInfoUpdateRequest newInstance() {
        return new ProductDetailInfoUpdateRequest(
                name,
                description,
                barcode,
                expirationDuration
        );
    }

    private final static class Constants {
        private static final String NAME = "name";
        private static final String DESCRIPTION = "description";
        private static final String BARCODE = "barcode";
        private static final String EXPIRATION_DURATION = "expirationDuration";
    }
}
