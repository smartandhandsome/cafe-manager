package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.SizeRegistrationForm;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProductRegistrationRequest(
        @NotNull
        Long productCategoryId,
        @PositiveOrZero
        int basePrice,
        @PositiveOrZero
        int baseCost,
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        String barcode,
        @NotNull
        String expirationDuration,
        @Valid
        @Size(min = 1)
        @NotNull
        List<SizeRegistrationRequest> sizeList
) {

    public ProductInfoRegistrationForm toProductInfoRegistrationForm() {
        return ProductInfoRegistrationForm.builder()
                .productCategoryId(productCategoryId)
                .basePrice(basePrice)
                .baseCost(baseCost)
                .name(name)
                .description(description)
                .barcode(barcode)
                .expirationDuration(expirationDuration)
                .build();
    }


    public List<SizeRegistrationForm> toSizeRegistrationFormList() {
        return sizeList.stream()
                .map(SizeRegistrationRequest::toSizeRegistrationForm)
                .toList();
    }

}
