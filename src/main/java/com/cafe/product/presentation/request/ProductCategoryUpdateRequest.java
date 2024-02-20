package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.cateory.ProductCategoryUpdateForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "상품 디테일 정보 수정 객체")
public record ProductCategoryUpdateRequest(
        @NotBlank
        @Schema(description = "카테고리 이름")
        String name
) {

    public ProductCategoryUpdateForm toProductCategoryUpdateForm(Long productCategoryId) {
        return new ProductCategoryUpdateForm(productCategoryId, name);
    }
}
