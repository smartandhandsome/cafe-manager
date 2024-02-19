package com.cafe.product.service.vo;

public record ProductPriceInfoUpdateForm(
        Long productInfoId,
        int basePrice,
        int baseCost
) {
}
