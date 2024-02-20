package com.cafe.product.service.vo;

public record ProductListView(
        Long productInfoId,
        int basePrice,
        String productInfoName,
        Long productCategoryId,
        String productCategoryName
) {
}
