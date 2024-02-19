package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.SizeRegistrationForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "상품 등록 요청 객체")
public record ProductRegistrationRequest(
        @Schema(description = "카테고리 id")
        @Positive
        @NotNull
        Long productCategoryId,
        @Schema(description = "기본 가격")
        @PositiveOrZero
        int basePrice,
        @Schema(description = "기본 원가")
        @PositiveOrZero
        int baseCost,
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
        @NotNull
        String expirationDuration,
        @Schema(description = "사이즈 등록 요청 객체 목록")
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
