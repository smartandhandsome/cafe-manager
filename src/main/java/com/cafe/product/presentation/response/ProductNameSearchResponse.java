package com.cafe.product.presentation.response;

import com.cafe.product.service.vo.search.NameSearchResult;

import java.util.List;

public record ProductNameSearchResponse(
        List<ProductSearchViewResponse> searchResult
) {
    public static ProductNameSearchResponse from(NameSearchResult result) {
        List<ProductSearchViewResponse> searchResults = result.nameSearchViews()
                .stream()
                .map(nameSearchView ->
                        new ProductSearchViewResponse(nameSearchView.productInfoId(), nameSearchView.name())
                ).toList();
        return new ProductNameSearchResponse(searchResults);
    }

    public record ProductSearchViewResponse(
            Long productInfoId,
            String name
    ) {

    }

}
