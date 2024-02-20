package com.cafe.product.service.vo.info;

public record ProductPriceInfoUpdateForm(
        Long productInfoId,
        int basePrice,
        int baseCost
) {
}
