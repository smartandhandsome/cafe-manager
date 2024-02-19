package com.cafe.product.service.vo;

public record ProductSizePriceUpdateForm(
        Long productSizeId,
        int extraCharge,
        int extraCost
) {
}
