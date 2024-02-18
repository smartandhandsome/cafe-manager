package com.cafe.product.service.vo;

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
