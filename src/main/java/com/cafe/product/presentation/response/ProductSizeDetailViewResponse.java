package com.cafe.product.presentation.response;

import com.cafe.product.service.vo.ProductSizeDetailView;
import lombok.Builder;

@Builder
public record ProductSizeDetailViewResponse(
        Long productSizeId,
        String name,
        int extraCharge,
        int extraCost
) {

    public static ProductSizeDetailViewResponse from(ProductSizeDetailView productSizeDetailView) {
        return ProductSizeDetailViewResponse.builder()
                .productSizeId(productSizeDetailView.productSizeId())
                .name(productSizeDetailView.name())
                .extraCharge(productSizeDetailView.extraCharge())
                .extraCost(productSizeDetailView.extraCost())
                .build();
    }

}
