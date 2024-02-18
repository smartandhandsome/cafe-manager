package com.cafe.product.presentation;

import com.cafe.common.model.Authentication;
import com.cafe.common.model.MyCafeResponse;
import com.cafe.product.presentation.request.ProductRegistrationRequest;
import com.cafe.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @Operation(description = "상품 정보 추가", security = @SecurityRequirement(name = "Authorization"))
    @PostMapping("/infos")
    public MyCafeResponse<Void> requestProductRegistration(
            Authentication authentication,
            @RequestBody @Valid ProductRegistrationRequest productRegistrationRequest
    ) {
        productService.register(
                productRegistrationRequest.toProductInfoRegistrationForm(),
                productRegistrationRequest.toSizeRegistrationFormList()
        );
        return MyCafeResponse.success();
    }

    // TODO: 2/19/24 /categories

}
