package com.cafe.product.presentation;

import com.cafe.common.model.AdminAuthorization;
import com.cafe.common.model.MyCafeResponse;
import com.cafe.product.presentation.request.ProductCategoryRegistrationRequest;
import com.cafe.product.presentation.request.ProductCategoryUpdateRequest;
import com.cafe.product.presentation.request.ProductDetailInfoUpdateRequest;
import com.cafe.product.presentation.request.ProductPriceInfoUpdateRequest;
import com.cafe.product.presentation.request.ProductRegistrationRequest;
import com.cafe.product.presentation.request.ProductSizeInfoUpdateRequest;
import com.cafe.product.presentation.request.ProductSizePriceUpdateRequest;
import com.cafe.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Product", description = "상품 API")
@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(description = "상품 카테고리 추가", security = @SecurityRequirement(name = "Authorization"))
    @PostMapping("/categories")
    public MyCafeResponse<Void> requestProductCategoryRegistration(
            AdminAuthorization adminAuthorization,
            @RequestBody @Valid ProductCategoryRegistrationRequest productCategoryRegistrationRequest
    ) {
        productService.register(productCategoryRegistrationRequest.toProductCategoryRegistrationForm());
        return MyCafeResponse.success();
    }

    @Operation(description = "상품 정보 추가", security = @SecurityRequirement(name = "Authorization"))
    @PostMapping("/infos")
    public MyCafeResponse<Void> requestProductRegistration(
            AdminAuthorization adminAuthorization,
            @RequestBody @Valid ProductRegistrationRequest productRegistrationRequest
    ) {
        productService.register(
                productRegistrationRequest.toProductInfoRegistrationForm(),
                productRegistrationRequest.toSizeRegistrationFormList()
        );
        return MyCafeResponse.success();
    }

    @Operation(description = "상품 가격 정보 수정", security = @SecurityRequirement(name = "Authorization"))
    @PatchMapping("/infos/{productInfoId}/price")
    public MyCafeResponse<Void> requestProductPriceInfoUpdate(
            AdminAuthorization adminAuthorization,
            @PathVariable Long productInfoId,
            @RequestBody @Valid ProductPriceInfoUpdateRequest productPriceInfoUpdateRequest
    ) {
        productService.update(
                productPriceInfoUpdateRequest.toProductPriceInfoUpdateForm(productInfoId)
        );
        return MyCafeResponse.success();
    }

    @Operation(description = "상품 상세 정보 수정", security = @SecurityRequirement(name = "Authorization"))
    @PatchMapping("/infos/{productInfoId}/detail")
    public MyCafeResponse<Void> requestProductDetailInfoUpdate(
            AdminAuthorization adminAuthorization,
            @PathVariable Long productInfoId,
            @RequestBody @Valid ProductDetailInfoUpdateRequest productDetailInfoUpdateRequest
    ) {
        productService.update(
                productDetailInfoUpdateRequest.toProductDetailInfoUpdateForm(productInfoId)
        );
        return MyCafeResponse.success();
    }

    @Operation(description = "상품 카테고리 수정", security = @SecurityRequirement(name = "Authorization"))
    @PatchMapping("/categories/{productCategoryId}")
    public MyCafeResponse<Void> requestProductCategoryUpdate(
            AdminAuthorization adminAuthorization,
            @PathVariable Long productCategoryId,
            @RequestBody @Valid ProductCategoryUpdateRequest productCategoryUpdateRequest
    ) {
        productService.update(productCategoryUpdateRequest.toProductCategoryUpdateForm(productCategoryId));
        return MyCafeResponse.success();
    }

    @Operation(description = "상품 사이즈 가격 수정", security = @SecurityRequirement(name = "Authorization"))
    @PatchMapping("/sizes/{productSizeId}/price")
    public MyCafeResponse<Void> requestProductSizePriceUpdate(
            AdminAuthorization adminAuthorization,
            @PathVariable Long productSizeId,
            @RequestBody @Valid ProductSizePriceUpdateRequest productSizePriceUpdateRequest
    ) {
        productService.update(productSizePriceUpdateRequest.toProductSizePriceUpdateForm(productSizeId));
        return MyCafeResponse.success();
    }

    @Operation(description = "상품 사이즈 정보 수정", security = @SecurityRequirement(name = "Authorization"))
    @PatchMapping("/sizes/{productSizeId}/info")
    public MyCafeResponse<Void> requestProductSizeInfoUpdate(
            AdminAuthorization adminAuthorization,
            @PathVariable Long productSizeId,
            @RequestBody @Valid ProductSizeInfoUpdateRequest productSizeInfoUpdateRequest
    ) {
        productService.update(productSizeInfoUpdateRequest.toProductSizeUpdateForm(productSizeId));
        return MyCafeResponse.success();
    }

}
