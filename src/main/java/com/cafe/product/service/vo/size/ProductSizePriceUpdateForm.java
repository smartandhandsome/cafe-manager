package com.cafe.product.service.vo.size;

public record ProductSizePriceUpdateForm(
        Long productSizeId,
        int extraCharge,
        int extraCost
) {
}
