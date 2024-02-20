package com.cafe.product.service.vo.size;

public enum ProductSizeDetailViewFixture {
    SMALL(1L, "Small", 50, 30),
    LARGE(2L, "Large", 150, 90),
    ;

    private final Long productSizeId;
    private final String name;
    private final int extraCharge;
    private final int extraCost;

    ProductSizeDetailViewFixture(Long productSizeId, String name, int extraCharge, int extraCost) {
        this.productSizeId = productSizeId;
        this.name = name;
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
    }

    public ProductSizeDetailView newInstance() {
        return ProductSizeDetailView.builder()
                .productSizeId(productSizeId)
                .name(name)
                .extraCharge(extraCharge)
                .extraCost(extraCost)
                .build();
    }

}
