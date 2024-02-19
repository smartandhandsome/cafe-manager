package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDetailInfoUpdateRequest(
        @Schema(description = "상품 정보 id")
        @Positive
        @NotNull
        Long productInfoId,
        @Schema(description = "이름")
        @NotBlank
        String name,
        @Schema(description = "설명")
        @NotBlank
        String description,
        @Schema(description = "바코드")
        @NotBlank
        String barcode,
        @Schema(description = "유통기한")
        @NotBlank
        String expirationDuration
) {

    public ProductDetailInfoUpdateForm toProductDetailInfoUpdateForm() {
        return new ProductDetailInfoUpdateForm(
                productInfoId,
                name,
                description,
                barcode,
                expirationDuration
        );
    }
}
