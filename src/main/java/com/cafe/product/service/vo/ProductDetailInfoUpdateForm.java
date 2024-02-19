package com.cafe.product.service.vo;

public record ProductDetailInfoUpdateForm(
        Long productInfoId,
        String name,
        String description,
        String barcode,
        String expirationDuration
) {
}
