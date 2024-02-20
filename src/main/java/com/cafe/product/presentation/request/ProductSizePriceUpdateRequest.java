package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.size.ProductSizePriceUpdateForm;

public record ProductSizePriceUpdateRequest(
        int extraCharge,
        int extraCost
) {

    public ProductSizePriceUpdateForm toProductSizePriceUpdateForm(Long productSizeId) {
        return new ProductSizePriceUpdateForm(
                productSizeId,
                extraCharge,
                extraCost
        );
    }

}
