package com.cafe.product.presentation;

import com.cafe.product.service.vo.ProductCategoryUpdateForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "상품 디테일 정보 수정 객체")
public record ProductCategoryUpdateRequest(
        @Schema(description = "카테고리 id")
        @Positive
        @NotNull
        Long productCategoryId,
        @NotBlank
        @Schema(description = "카테고리 이름")
        String name
) {

    public ProductCategoryUpdateForm toProductCategoryUpdateForm() {
        return new ProductCategoryUpdateForm(productCategoryId, name);
    }
}
