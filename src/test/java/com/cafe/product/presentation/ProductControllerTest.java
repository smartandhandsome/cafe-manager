package com.cafe.product.presentation;

import com.cafe.common.model.AdminAuthorization;
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
import com.cafe.product.presentation.response.ProductDetailViewResponse;
import com.cafe.product.presentation.response.ProductListViewListResponse;
import com.cafe.product.presentation.response.ProductListViewListResponseFixture;
import com.cafe.product.presentation.response.ProductNameSearchResponse;
import com.cafe.product.service.ProductCommandService;
import com.cafe.product.service.ProductQueryService;
import com.cafe.product.service.vo.ProductDetailView;
import com.cafe.product.service.vo.ProductDetailViewFixture;
import com.cafe.product.service.vo.ProductListViewList;
import com.cafe.product.service.vo.search.NameSearchResult;
import com.cafe.product.service.vo.search.NameSearchViewFixture;
import com.cafe.product.service.vo.size.ProductSizeDetailView;
import com.cafe.product.service.vo.size.ProductSizeDetailViewFixture;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest extends AdminAuthorizationControllerTest {

    String BASE_URI = "/v1/products";

    @MockBean
    ProductCommandService productCommandService;
    @MockBean
    ProductQueryService productQueryService;
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
                        post(BASE_URI + "/categories")
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productCommandService).should().register(request.toProductCategoryRegistrationForm());
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
                        post(BASE_URI + "/infos")
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productCommandService).should().register(productRegistrationRequest.toProductInfoRegistrationForm(), productRegistrationRequest.toSizeRegistrationFormList());
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
                        patch(BASE_URI + "/infos/{productInfoId}/price", productInfoId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productCommandService).should(times(1)).update(request.toProductPriceInfoUpdateForm(productInfoId));
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
                        patch(BASE_URI + "/infos/{productInfoId}/detail", productInfoId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productCommandService).should(times(1)).update(request.toProductDetailInfoUpdateForm(productInfoId));
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
                        patch(BASE_URI + "/categories/{productCategoryId}", productCategoryId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productCommandService).should(times(1)).update(request.toProductCategoryUpdateForm(productCategoryId));
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
                        patch(BASE_URI + "/sizes/{productSizeId}/price", productSizeId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productCommandService).should(times(1)).update(request.toProductSizePriceUpdateForm(productSizeId));
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
                        patch(BASE_URI + "/sizes/{productSizeId}/info", productSizeId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productCommandService).should(times(1)).update(request.toProductSizeUpdateForm(productSizeId));
    }

    @Test
    void 상품_사이즈_삭제_요청을_할_수_있다() throws Exception {
        // given
        Long productSizeId = 1L;

        MyCafeResponse<Void> response = MyCafeResponse.success();

        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        delete(BASE_URI + "/sizes/{productSizeId}", productSizeId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productCommandService).should(times(1)).deleteProductSize(productSizeId);
    }

    @Test
    void 상품_정보_삭제_요청을_할_수_있다() throws Exception {
        // given
        Long productInfoId = 1L;

        MyCafeResponse<Void> response = MyCafeResponse.success();

        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        delete(BASE_URI + "/infos/{productInfoId}", productInfoId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));

        then(productCommandService).should(times(1)).deleteProductInfo(productInfoId);
    }

    @Test
    void 상품_카테고리_삭제_요청을_할_수_있다() throws Exception {
        // given
        Long productCategoryId = 1L;

        MyCafeResponse<Void> response = MyCafeResponse.success();

        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        delete(BASE_URI + "/categories/{productCategoryId}", productCategoryId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));


        then(productCommandService).should(times(1)).deleteProductCategory(productCategoryId);
    }

    @Nested
    class 상품_리스트_조회_요청을_할_수_있다 {

        @Test
        void lastProductId_파라미터_있을_때() throws Exception {
            // given
            Long lastProductId = 1L;

            ProductListViewList viewList = new ProductListViewList(List.of(), false);
            ProductListViewListResponse viewListResponse = ProductListViewListResponseFixture.EMPTY.newInstance();
            MyCafeResponse<ProductListViewListResponse> response = MyCafeResponse.success(viewListResponse);

            given(productQueryService.queryProductList(lastProductId)).willReturn(viewList);

            String responseBody = om.writeValueAsString(response);

            // when
            mvc.perform(
                            get(BASE_URI + "/list")
                                    .param("lastProductId", String.valueOf(lastProductId))
                                    .header(AUTHORIZATION, authorizationHeader)
                                    .contentType(APPLICATION_JSON)
                    )
                    // then
                    .andExpect(status().isOk())
                    .andExpect(content().json(responseBody));
        }

        @Test
        void lastProductId_파라미터_없을_때() throws Exception {
            // given
            ProductListViewList viewList = new ProductListViewList(List.of(), false);
            ProductListViewListResponse viewListResponse =
                    ProductListViewListResponseFixture.EMPTY.newInstance();
            MyCafeResponse<ProductListViewListResponse> response = MyCafeResponse.success(viewListResponse);

            given(productQueryService.queryProductList(0L)).willReturn(viewList);

            String responseBody = om.writeValueAsString(response);

            // when
            mvc.perform(
                            get(BASE_URI + "/list")
                                    .header(AUTHORIZATION, authorizationHeader)
                                    .contentType(APPLICATION_JSON)
                    )
                    // then
                    .andExpect(status().isOk())
                    .andExpect(content().json(responseBody));
        }

    }

    @Test
    void 상품_상세_조회를_요청할_수_있다() throws Exception {
        // given
        List<ProductSizeDetailView> productSizeDetailViewList = List.of(
                ProductSizeDetailViewFixture.SMALL.newInstance(),
                ProductSizeDetailViewFixture.LARGE.newInstance()
        );
        ProductDetailView detailView = ProductDetailViewFixture.STANDARD.newInstance(productSizeDetailViewList);

        Long productInfoId = detailView.productInfoId();

        given(productQueryService.queryProductDetail(productInfoId)).willReturn(detailView);

        ProductDetailViewResponse detailViewResponse = ProductDetailViewResponse.from(detailView);
        MyCafeResponse<ProductDetailViewResponse> response = MyCafeResponse.success(detailViewResponse);

        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        get(BASE_URI + "/{productInfoId}", productInfoId)
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void 상품_이름_검색을_요청할_수_있다() throws Exception {
        // given
        String name = "라뗴";

        NameSearchResult nameSearchResult = new NameSearchResult(
                List.of(NameSearchViewFixture.LATTE.newInstance(), NameSearchViewFixture.ICE_LATTE.newInstance())
        );

        given(productQueryService.searchName(name)).willReturn(nameSearchResult);

        ProductNameSearchResponse productNameSearchResponse = ProductNameSearchResponse.from(nameSearchResult);
        MyCafeResponse<ProductNameSearchResponse> response = MyCafeResponse.success(productNameSearchResponse);

        String responseBody = om.writeValueAsString(response);

        // when
        mvc.perform(
                        get(BASE_URI + "/search")
                                .header(AUTHORIZATION, authorizationHeader)
                                .contentType(APPLICATION_JSON)
                                .param("name", name)
                )
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

}
