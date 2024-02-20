package com.cafe.product.service.vo;

import java.util.List;

public enum ProductListViewListFixture {
    EMPTY(
            List.of(),
            false
    ),
    ;

    private List<ProductListView> viewList;
    private boolean hasNext;

    ProductListViewListFixture(List<ProductListView> viewList, boolean hasNext) {
        this.viewList = viewList;
        this.hasNext = hasNext;
    }

    public ProductListViewList newInstance() {
        return new ProductListViewList(
                viewList,
                hasNext
        );
    }

}

