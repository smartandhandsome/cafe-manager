package com.cafe.product.presentation.response;

import com.cafe.product.service.vo.ProductListViewList;

import java.util.List;

public record ProductListViewListResponse(
        List<ProductListViewResponse> productList,
        boolean hasNext,
        int size
) {

    public static ProductListViewListResponse from(ProductListViewList productListViewList) {
        List<ProductListViewResponse> viewResponses = productListViewList.viewList()
                .stream()
                .map(view ->
                        new ProductListViewResponse(
                                view.productInfoId(),
                                view.basePrice(),
                                view.productInfoName(),
                                view.productCategoryId(),
                                view.productCategoryName()
                        )
                ).toList();
        return new ProductListViewListResponse(viewResponses, productListViewList.hasNext(), viewResponses.size());
    }
}
