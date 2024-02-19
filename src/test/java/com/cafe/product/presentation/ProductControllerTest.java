package com.cafe.product.presentation;

import com.cafe.common.model.AdminAuthorizationControllerTest;
import com.cafe.common.model.MyCafeResponse;
import com.cafe.product.presentation.request.ProductCategoryRegistrationRequest;
import com.cafe.product.presentation.request.ProductCategoryRegistrationRequestFixture;
import com.cafe.product.presentation.request.ProductCategoryUpdateRequest;
import com.cafe.product.presentation.request.ProductCategoryUpdateRequestFixture;
import com.cafe.product.presentation.request.ProductDetailInfoUpdateRequest;
import com.cafe.product.presentation.request.ProductDetailInfoUpdateRequestFixture;
import com.cafe.product.presentation.request.ProductPriceInfoUpdateRequest;
import com.cafe.product.presentation.request.ProductPriceInfoUpdateRequestFixture;
import com.cafe.product.presentation.request.ProductRegistrationRequest;
import com.cafe.product.presentation.request.ProductRegistrationRequestFixture;
import com.cafe.product.presentation.request.ProductSizeInfoUpdateRequest;
import com.cafe.product.presentation.request.ProductSizeInfoUpdateRequestFixture;
import com.cafe.product.presentation.request.ProductSizePriceUpdateRequest;
import com.cafe.product.presentation.request.ProductSizePriceUpdateRequestFixture;
import com.cafe.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest extends AdminAuthorizationControllerTest {

    @MockBean
    ProductService productService;
    @Autowired
    private ProductController controller;

    @Test
    void 상품_카테고리_등록을_요청할_수_있다() throws Exception {
        // given
        ProductCategoryRegistrationRequest request = ProductCategoryRegistrationRequestFixture.STANDARD.newInstance();
        MyCafeResponse<Void> response = MyCafeResponse.success();

        String requestBody = om.writeValueAsString(request);
        String responseBody = om.writeValueAsString(response);

        mvc.perform(
                        post("/v1/products/categories")
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productService).should().register(request.toProductCategoryRegistrationForm());
    }

    @Test
    void 상품_등록을_요청할_수_있다() throws Exception {
        // given
        ProductRegistrationRequest productRegistrationRequest = ProductRegistrationRequestFixture.STANDARD.newInstance();
        MyCafeResponse<Void> successResponse = MyCafeResponse.success();

        String requestBody = om.writeValueAsString(productRegistrationRequest);
        String responseBody = om.writeValueAsString(successResponse);

        // when
        mvc.perform(
                        post("/v1/products/infos")
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productService).should().register(productRegistrationRequest.toProductInfoRegistrationForm(), productRegistrationRequest.toSizeRegistrationFormList());
    }

    @Test
    void 상품_가격_정보_수정을_요청할_수_있다() throws Exception {
        // given
        Long productInfoId = 1L;
        ProductPriceInfoUpdateRequest request = ProductPriceInfoUpdateRequestFixture.STANDARD.newInstance();
        MyCafeResponse<Void> response = MyCafeResponse.success();

        String requestBody = om.writeValueAsString(request);
        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        patch("/v1/products/infos/{productInfoId}/price", productInfoId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productService).should(times(1)).update(request.toProductPriceInfoUpdateForm(productInfoId));
    }

    @Test
    void 상품_디테일_정보_수정을_요청할_수_있다() throws Exception {
        // given
        Long productInfoId = 1L;
        ProductDetailInfoUpdateRequest request = ProductDetailInfoUpdateRequestFixture.STANDARD.newInstance();
        MyCafeResponse<Void> response = MyCafeResponse.success();

        String requestBody = om.writeValueAsString(request);
        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        patch("/v1/products/infos/{productInfoId}/detail", productInfoId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productService).should(times(1)).update(request.toProductDetailInfoUpdateForm(productInfoId));
    }

    @Test
    void 카테고리_수정을_요청할_수_있다() throws Exception {
        // given
        Long productCategoryId = 1L;
        ProductCategoryUpdateRequest request = ProductCategoryUpdateRequestFixture.STANDARD.newInstance();
        MyCafeResponse<Void> response = MyCafeResponse.success();

        String requestBody = om.writeValueAsString(request);
        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        patch("/v1/products/categories/{productCategoryId}", productCategoryId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productService).should(times(1)).update(request.toProductCategoryUpdateForm(productCategoryId));
    }

    @Test
    void 상품_사이즈_가격_수정을_요청할_수_있다() throws Exception {
        // given
        Long productSizeId = 1L;
        ProductSizePriceUpdateRequest request = ProductSizePriceUpdateRequestFixture.STANDARD.newInstance();
        MyCafeResponse<Void> response = MyCafeResponse.success();

        String requestBody = om.writeValueAsString(request);
        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        patch("/v1/products/sizes/{productSizeId}/price", productSizeId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productService).should(times(1)).update(request.toProductSizePriceUpdateForm(productSizeId));
    }

    @Test
    void 상품_사이즈_정보_수정을_요청할_수_있다() throws Exception {
        // given
        Long productSizeId = 1L;
        ProductSizeInfoUpdateRequest request = ProductSizeInfoUpdateRequestFixture.STANDARD.newInstance();
        MyCafeResponse<Void> response = MyCafeResponse.success();

        String requestBody = om.writeValueAsString(request);
        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        patch("/v1/products/sizes/{productSizeId}/info", productSizeId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productService).should(times(1)).update(request.toProductSizeUpdateForm(productSizeId));
    }


}
