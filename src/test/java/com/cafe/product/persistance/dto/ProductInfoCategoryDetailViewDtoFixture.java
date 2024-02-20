package com.cafe.product.persistance.dto;

public enum ProductInfoCategoryDetailViewDtoFixture {
    STANDARD(
            Constants.PRODUCT_INFO_ID,
            Constants.CATEGORY_ID,
            Constants.CATEGORY_NAME,
            Constants.PRODUCT_NAME,
            Constants.BASE_PRICE,
            Constants.BASE_COST,
            Constants.DESCRIPTION,
            Constants.BARCODE,
            Constants.EXPIRATION_DURATION
    );

    private final Long productInfoId;
    private final Long categoryId;
    private final String categoryName;
    private final String productName;
    private final int basePrice;
    private final int baseCost;
    private final String description;
    private final String barcode;
    private final String expirationDuration;

    ProductInfoCategoryDetailViewDtoFixture(Long productInfoId, Long categoryId, String categoryName, String productName, int basePrice, int baseCost, String description, String barcode, String expirationDuration) {
        this.productInfoId = productInfoId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productName = productName;
        this.basePrice = basePrice;
        this.baseCost = baseCost;
        this.description = description;
        this.barcode = barcode;
        this.expirationDuration = expirationDuration;
    }

    public ProductInfoCategoryDetailViewDto newInstance() {
        return ProductInfoCategoryDetailViewDto.builder()
                .productInfoId(productInfoId)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .productName(productName)
                .basePrice(basePrice)
                .baseCost(baseCost)
                .description(description)
                .barcode(barcode)
                .expirationDuration(expirationDuration)
                .build();
    }

    private static final class Constants {
        private static final Long CATEGORY_ID = 1L;
        private static final String CATEGORY_NAME = "categoryName";
        private static final Long PRODUCT_INFO_ID = 100L;
        private static final int BASE_PRICE = 500;
        private static final int BASE_COST = 300;
        private static final String PRODUCT_NAME = "productName";
        private static final String DESCRIPTION = "description";
        private static final String BARCODE = "123456789";
        private static final String EXPIRATION_DURATION = "expirationDuration";
    }
}

