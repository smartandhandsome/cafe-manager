package com.cafe.product.persistance.dto;

import com.cafe.product.service.vo.ProductListView;

public record ProductListViewDto(Long productInfoId, Long categoryId, String categoryName, String productName,
                                 int basePrice) {
    public ProductListView toProductListView() {
        return new ProductListView(
                productInfoId,
                basePrice,
                productName,
                categoryId,
                categoryName
        );
    }

}
