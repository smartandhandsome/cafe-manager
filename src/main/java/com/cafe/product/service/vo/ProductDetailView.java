package com.cafe.product.service.vo;

import com.cafe.product.persistance.dto.ProductInfoCategoryDetailViewDto;
import com.cafe.product.persistance.dto.ProductSizeDetailViewDto;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductDetailView(
        Long categoryId,
        String categoryName,
        Long productInfoId,
        int basePrice,
        int baseCost,
        String productName,
        String description,
        String barcode,
        String expirationDuration,
        List<ProductSizeDetailView> productSizeDetailViewList
) {

    public static ProductDetailView of(
            ProductInfoCategoryDetailViewDto productInfoCategoryDetailViewDto,
            List<ProductSizeDetailViewDto> productSizeDetailViewDtos
    ) {
        List<ProductSizeDetailView> productSizeDetailViewList = productSizeDetailViewDtos.stream()
                .map(ProductSizeDetailView::from)
                .toList();

        return ProductDetailView.builder()
                .categoryId(productInfoCategoryDetailViewDto.categoryId())
                .categoryName(productInfoCategoryDetailViewDto.categoryName())
                .productInfoId(productInfoCategoryDetailViewDto.productInfoId())
                .basePrice(productInfoCategoryDetailViewDto.basePrice())
                .baseCost(productInfoCategoryDetailViewDto.baseCost())
                .productName(productInfoCategoryDetailViewDto.productName())
                .description(productInfoCategoryDetailViewDto.description())
                .barcode(productInfoCategoryDetailViewDto.barcode())
                .expirationDuration(productInfoCategoryDetailViewDto.expirationDuration())
                .productSizeDetailViewList(productSizeDetailViewList)
                .build();
    }

}
