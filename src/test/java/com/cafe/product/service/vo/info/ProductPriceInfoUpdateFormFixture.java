package com.cafe.product.service.vo.info;

public enum ProductPriceInfoUpdateFormFixture {
    STANDARD(
            Constants.ID,
            Constants.HUNDRED,
            Constants.HUNDRED
    ),
    ;

    private final Long productInfoId;
    private final int basePrice;
    private final int baseCost;

    ProductPriceInfoUpdateFormFixture(Long productInfoId, int basePrice, int baseCost) {
        this.productInfoId = productInfoId;
        this.basePrice = basePrice;
        this.baseCost = baseCost;
    }

    public ProductPriceInfoUpdateForm newInstance() {
        return new ProductPriceInfoUpdateForm(
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
