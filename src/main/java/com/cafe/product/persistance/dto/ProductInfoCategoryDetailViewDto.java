package com.cafe.product.persistance.dto;

import lombok.Builder;

@Builder
public record ProductInfoCategoryDetailViewDto(
        Long productInfoId,
        Long categoryId,
        String categoryName,
        String productName,
        int basePrice,
        int baseCost,
        String description,
        String barcode,
        String expirationDuration
) {
}
