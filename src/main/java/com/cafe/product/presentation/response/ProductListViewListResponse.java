package com.cafe.product.presentation.response;

import java.util.List;

public record ProductListViewListResponse(
        List<ProductListViewResponse> viewResponseList,
        boolean hasNext,
        int size
) {
}
