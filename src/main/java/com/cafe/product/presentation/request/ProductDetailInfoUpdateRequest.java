package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.info.ProductDetailInfoUpdateForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "상품 디테일 정보 수정 객체")
public record ProductDetailInfoUpdateRequest(
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

    public ProductDetailInfoUpdateForm toProductDetailInfoUpdateForm(Long productInfoId) {
        return new ProductDetailInfoUpdateForm(
                productInfoId,
                name,
                description,
                barcode,
                expirationDuration
        );
    }
}
