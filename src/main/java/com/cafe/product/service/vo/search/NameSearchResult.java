package com.cafe.product.service.vo.search;

import java.util.List;

public record NameSearchResult(
        List<NameSearchView> nameSearchViews
) {
}
