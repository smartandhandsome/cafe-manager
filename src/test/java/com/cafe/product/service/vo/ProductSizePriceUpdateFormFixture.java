package com.cafe.product.service.vo;

import com.cafe.product.service.vo.size.ProductSizePriceUpdateForm;

public enum ProductSizePriceUpdateFormFixture {
    STANDARD(
            Constants.ID,
            Constants.HUNDRED,
            Constants.HUNDRED
    ),
    ;

    private final Long productSizeId;
    private final int extraCharge;
    private final int extraCost;

    ProductSizePriceUpdateFormFixture(Long productSizeId, int extraCharge, int extraCost) {
        this.productSizeId = productSizeId;
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
    }

    public ProductSizePriceUpdateForm newInstance() {
        return new ProductSizePriceUpdateForm(productSizeId, extraCharge, extraCost);
    }

    private static final class Constants {
        private static final Long ID = 1L;
        private static final int HUNDRED = 100;
    }
}

