package com.cafe.product.service.impl.search;

import com.cafe.product.service.impl.info.ProductInfoSearch;
import com.cafe.product.service.vo.search.NameSearchResult;
import com.cafe.product.service.vo.search.NameSearchStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultSearchEngine implements SearchEngine {

    private final ProductInfoSearch productInfoSearch;

    @Override
    public NameSearchResult search(String name) {
        return productInfoSearch.searchComplete(name);
    }

    @Override
    public NameSearchStrategy getStrategy() {
        return NameSearchStrategy.DEFAULT;
    }
}
