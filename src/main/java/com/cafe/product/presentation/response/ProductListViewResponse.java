package com.cafe.product.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "상품 리스트 조회 응답 객체")
public record ProductListViewResponse(
        @Schema(name = "상품 정보 아이디")
        Long productInfoId,
        @Schema(name = "상품 기본 가격")
        int basePrice,
        @Schema(name = "상품 정보 이름")
        String productInfoName,
        @Schema(name = "상품 카테고리 아이디")
        Long productCategoryId,
        @Schema(name = "상품 카테고리 이름")
        String productCategoryName
) {
}
