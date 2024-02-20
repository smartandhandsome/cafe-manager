package com.cafe.product.persistance.dto;

public enum ProductSizeDetailViewDtoFixture {
    SMALL(Constants.SMALL_PRODUCT_SIZE_ID, Constants.SMALL_NAME, Constants.EXTRA_CHARGE, Constants.EXTRA_COST),
    LARGE(Constants.LARGE_PRODUCT_SIZE_ID, Constants.LARGE_NAME, Constants.EXTRA_CHARGE, Constants.EXTRA_COST);

    private final Long productSizeId;
    private final String name;
    private final int extraCharge;
    private final int extraCost;

    ProductSizeDetailViewDtoFixture(Long productSizeId, String name, int extraCharge, int extraCost) {
        this.productSizeId = productSizeId;
        this.name = name;
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
    }

    public ProductSizeDetailViewDto newInstance() {
        return ProductSizeDetailViewDto.builder()
                .productSizeId(productSizeId)
                .name(name)
                .extraCharge(extraCharge)
                .extraCost(extraCost)
                .build();
    }

    private static class Constants {
        static final Long SMALL_PRODUCT_SIZE_ID = 1L;
        static final String SMALL_NAME = "Small";
        static final int EXTRA_CHARGE = 50;
        static final int EXTRA_COST = 20;

        static final Long LARGE_PRODUCT_SIZE_ID = 2L;
        static final String LARGE_NAME = "Large";
    }
}
