package com.cafe.product.service.vo;

import com.cafe.product.presentation.response.ProductListViewListResponse;
import com.cafe.product.presentation.response.ProductListViewResponse;

import java.util.List;

public record ProductListViewList(
        List<ProductListView> viewList,
        boolean hasNext
) {
    public ProductListViewListResponse toProductListViewListResponse() {
        List<ProductListViewResponse> viewResponses = viewList.stream()
                .map(view ->
                        new ProductListViewResponse(
                                view.productInfoId(),
                                view.basePrice(),
                                view.productInfoName(),
                                view.productCategoryId(),
                                view.productCategoryName()
                        )
                ).toList();
        return new ProductListViewListResponse(viewResponses, hasNext, viewList.size());
    }

}
