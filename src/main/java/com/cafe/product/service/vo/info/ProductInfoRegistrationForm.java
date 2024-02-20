package com.cafe.product.service.vo.info;

import lombok.Builder;

@Builder
public record ProductInfoRegistrationForm(
        Long productCategoryId,
        int basePrice,
        int baseCost,
        String name,
        String description,
        String barcode,
        String expirationDuration
) {
}
