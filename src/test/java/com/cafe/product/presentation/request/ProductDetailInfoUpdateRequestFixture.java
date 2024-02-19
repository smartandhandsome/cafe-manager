package com.cafe.product.presentation.request;

public enum ProductDetailInfoUpdateRequestFixture {
    STANDARD(
            Constants.ID,
            Constants.NAME,
            Constants.DESCRIPTION,
            Constants.BARCODE,
            Constants.EXPIRATION_DURATION
    ),
    ;

    private final Long productInfoId;
    private final String name;
    private final String description;
    private final String barcode;
    private final String expirationDuration;

    ProductDetailInfoUpdateRequestFixture(Long productInfoId, String name, String description, String barcode, String expirationDuration) {
        this.productInfoId = productInfoId;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.expirationDuration = expirationDuration;
    }

    public ProductDetailInfoUpdateRequest newInstance() {
        return new ProductDetailInfoUpdateRequest(
                productInfoId,
                name,
                description,
                barcode,
                expirationDuration
        );
    }

    private final static class Constants {
        private static final Long ID = 1L;
        private static final String NAME = "name";
        private static final String DESCRIPTION = "description";
        private static final String BARCODE = "barcode";
        private static final String EXPIRATION_DURATION = "expirationDuration";
    }
}
