package com.cafe.product.service.vo.info;

import lombok.Builder;

@Builder
public record PreprocessedProductInfoRegistrationForm(
        Long productCategoryId,
        int basePrice,
        int baseCost,
        String nameChosung,
        String name,
        String description,
        String barcode,
        String expirationDuration
) {
    public static PreprocessedProductInfoRegistrationForm of(
            ProductInfoRegistrationForm productInfoRegistrationForm,
            String nameChosung
    ) {
        return PreprocessedProductInfoRegistrationForm.builder()
                .productCategoryId(productInfoRegistrationForm.productCategoryId())
                .basePrice(productInfoRegistrationForm.basePrice())
                .baseCost(productInfoRegistrationForm.baseCost())
                .nameChosung(nameChosung)
                .name(productInfoRegistrationForm.name())
                .description(productInfoRegistrationForm.description())
                .barcode(productInfoRegistrationForm.barcode())
                .expirationDuration(productInfoRegistrationForm.expirationDuration())
                .build();
    }
}
