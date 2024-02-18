package com.cafe.product.presentation;

import com.cafe.admin.presentation.AdminController;
import com.cafe.admin.presentation.request.SignUpRequest;
import com.cafe.admin.presentation.request.SignUpRequestFixture;
import com.cafe.common.config.ControllerTestConfig;
import com.cafe.common.model.MyCafeResponse;
import com.cafe.product.presentation.request.ProductRegistrationRequest;
import com.cafe.product.presentation.request.ProductRegistrationRequestFixture;
import com.cafe.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(ControllerTestConfig.class)
@WebMvcTest(ProductController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @MockBean
    ProductService productService;
    @Autowired
    private ProductController controller;

    @Test
    void 상품등록을_요청할_수_있다() throws Exception {
        // given
        ProductRegistrationRequest productRegistrationRequest = ProductRegistrationRequestFixture.STANDARD.newInstance();
        MyCafeResponse<Void> successResponse = MyCafeResponse.success();

        String requestBody = om.writeValueAsString(productRegistrationRequest);
        String responseBody = om.writeValueAsString(successResponse);

        // when
        mvc.perform(
                        post("/v1/products")
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productService).should().register(productRegistrationRequest.toProductInfoRegistrationForm(), productRegistrationRequest.toSizeRegistrationFormList());
    }
}
