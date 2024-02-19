package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "기격 정보 수정 요청 객체")
public record ProductPriceInfoUpdateRequest(
        @NotNull
        @Positive
        Long productInfoId,
        @Schema(description = "변경할 기본 가격")
        @PositiveOrZero
        int basePrice,
        @Schema(description = "변경할 기본 원가")
        @PositiveOrZero
        int baseCost
) {

    public ProductPriceInfoUpdateForm toProductPriceInfoUpdateForm() {
        return new ProductPriceInfoUpdateForm(productInfoId, basePrice, baseCost);
    }

}
