package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.cateory.ProductCategoryRegistrationForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "카테고리 등록 요청 객체")
public record ProductCategoryRegistrationRequest(
        @Schema(description = "카테고리 이름")
        @NotBlank
        String name
) {

    public ProductCategoryRegistrationForm toProductCategoryRegistrationForm() {
        return new ProductCategoryRegistrationForm(name);
    }

}
