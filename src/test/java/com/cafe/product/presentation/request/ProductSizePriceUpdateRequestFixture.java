package com.cafe.product.presentation.request;

public enum ProductSizePriceUpdateRequestFixture {
    STANDARD(
            Constants.HUNDRED,
            Constants.HUNDRED
    ),
    ;

    private final int extraCharge;
    private final int extraCost;

    ProductSizePriceUpdateRequestFixture(int extraCharge, int extraCost) {
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
    }

    public ProductSizePriceUpdateRequest newInstance() {
        return new ProductSizePriceUpdateRequest(extraCharge, extraCost);
    }

    private final static class Constants {
        private final static int HUNDRED = 100;
    }

}
