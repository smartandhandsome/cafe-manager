package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.ProductSizeInfoUpdateForm;

public record ProductSizeInfoUpdateRequest(
        String name
) {

    public ProductSizeInfoUpdateForm toProductSizeUpdateForm(Long productSizeId) {
        return new ProductSizeInfoUpdateForm(productSizeId, name);
    }

}
