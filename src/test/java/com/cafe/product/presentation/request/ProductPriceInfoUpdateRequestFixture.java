package com.cafe.product.presentation.request;

public enum ProductPriceInfoUpdateRequestFixture {
    STANDARD(
            Constants.ID,
            Constants.HUNDRED,
            Constants.HUNDRED
    ),
    ;

    private final Long productInfoId;
    private final int basePrice;
    private final int baseCost;

    ProductPriceInfoUpdateRequestFixture(Long productInfoId, int basePrice, int baseCost) {
        this.productInfoId = productInfoId;
        this.basePrice = basePrice;
        this.baseCost = baseCost;
    }

    public ProductPriceInfoUpdateRequest newInstance() {
        return new ProductPriceInfoUpdateRequest(
                productInfoId,
                basePrice,
                baseCost
        );
    }

    private static final class Constants {
        private static final Long ID = 1L;
        private static final int HUNDRED = 100;
    }

}
