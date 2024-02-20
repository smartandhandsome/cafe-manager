package com.cafe.product.service.vo.info;

public record ProductDetailInfoUpdateForm(
        Long productInfoId,
        String name,
        String description,
        String barcode,
        String expirationDuration
) {
}
