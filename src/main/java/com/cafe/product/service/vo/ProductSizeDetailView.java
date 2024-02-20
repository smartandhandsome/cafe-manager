package com.cafe.product.service.vo;

import com.cafe.product.persistance.dto.ProductSizeDetailViewDto;
import lombok.Builder;

@Builder
public record ProductSizeDetailView(
        Long productSizeId,
        String name,
        int extraCharge,
        int extraCost
) {

    public static ProductSizeDetailView from(ProductSizeDetailViewDto dto) {
        return ProductSizeDetailView.builder()
                .productSizeId(dto.productSizeId())
                .name(dto.name())
                .extraCharge(dto.extraCharge())
                .extraCost(dto.extraCost())
                .build();
    }

}
