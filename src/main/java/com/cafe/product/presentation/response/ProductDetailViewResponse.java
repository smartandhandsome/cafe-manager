package com.cafe.product.presentation.response;

import com.cafe.product.service.vo.ProductDetailView;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductDetailViewResponse(
        Long categoryId,
        String categoryName,
        Long productInfoId,
        int basePrice,
        int baseCost,
        String productName,
        String description,
        String barcode,
        String expirationDuration,
        List<ProductSizeDetailViewResponse> productSizeDetailViewResponseList
) {

    public static ProductDetailViewResponse from(ProductDetailView productDetailView) {
        List<ProductSizeDetailViewResponse> productSizeDetailViewResponseList = productDetailView.productSizeDetailViewList()
                .stream()
                .map(ProductSizeDetailViewResponse::from)
                .toList();
        return ProductDetailViewResponse.builder()
                .categoryId(productDetailView.categoryId())
                .categoryName(productDetailView.categoryName())
                .productInfoId(productDetailView.productInfoId())
                .basePrice(productDetailView.basePrice())
                .baseCost(productDetailView.baseCost())
                .productName(productDetailView.productName())
                .description(productDetailView.description())
                .barcode(productDetailView.barcode())
                .expirationDuration(productDetailView.expirationDuration())
                .productSizeDetailViewResponseList(productSizeDetailViewResponseList)
                .build();
    }

}
