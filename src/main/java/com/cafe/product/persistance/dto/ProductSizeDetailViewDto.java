package com.cafe.product.persistance.dto;

import lombok.Builder;

@Builder
public record ProductSizeDetailViewDto(
        Long productSizeId,
        String name,
        int extraCharge,
        int extraCost
) {
}
