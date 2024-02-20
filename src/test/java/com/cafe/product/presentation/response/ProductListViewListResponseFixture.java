package com.cafe.product.presentation.response;

import java.util.List;

public enum ProductListViewListResponseFixture {
    EMPTY(
            List.of(),
            false,
            0
    ),
    ;

    private List<ProductListViewResponse> viewResponseList;
    private boolean hasNext;
    private int size;

    ProductListViewListResponseFixture(List<ProductListViewResponse> viewResponseList, boolean hasNext, int size) {
        this.viewResponseList = viewResponseList;
        this.hasNext = hasNext;
        this.size = size;
    }

    public ProductListViewListResponse newInstance() {
        return new ProductListViewListResponse(
                viewResponseList,
                hasNext,
                size
        );
    }

}
