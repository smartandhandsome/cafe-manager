package com.cafe.product.presentation.request;

public enum ProductPriceInfoUpdateRequestFixture {
    STANDARD(
            Constants.HUNDRED,
            Constants.HUNDRED
    ),
    ;

    private final int basePrice;
    private final int baseCost;

    ProductPriceInfoUpdateRequestFixture(int basePrice, int baseCost) {
        this.basePrice = basePrice;
        this.baseCost = baseCost;
    }

    public ProductPriceInfoUpdateRequest newInstance() {
        return new ProductPriceInfoUpdateRequest(
                basePrice,
                baseCost
        );
    }

    private static final class Constants {
        private static final int HUNDRED = 100;
    }

}
