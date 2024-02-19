package com.cafe.product.presentation;

import com.cafe.common.model.AdminAuthorizationControllerTest;
import com.cafe.common.model.MyCafeResponse;
import com.cafe.product.presentation.request.ProductCategoryRegistrationRequest;
import com.cafe.product.presentation.request.ProductCategoryRegistrationRequestFixture;
import com.cafe.product.presentation.request.ProductRegistrationRequest;
import com.cafe.product.presentation.request.ProductRegistrationRequestFixture;
import com.cafe.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;

import static org.mockito.BDDMockito.then;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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
}
