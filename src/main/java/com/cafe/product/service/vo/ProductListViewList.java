package com.cafe.product.service.vo;

import java.util.List;

public record ProductListViewList(
        List<ProductListView> viewList,
        boolean hasNext
) {

}
