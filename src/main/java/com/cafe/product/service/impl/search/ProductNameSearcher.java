package com.cafe.product.service.impl.search;

import com.cafe.product.service.vo.search.NameSearchResult;
import com.cafe.product.service.vo.search.NameSearchStrategy;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@Component
public class ProductNameSearcher {

    private final Map<NameSearchStrategy, SearchEngine> searchMap;

    public ProductNameSearcher(Set<SearchEngine> searchEngines) {
        searchMap = new EnumMap<>(NameSearchStrategy.class);
        searchEngines.forEach(searchEngine -> searchMap.put(searchEngine.getStrategy(), searchEngine));
    }

    public NameSearchResult search(NameSearchStrategy strategy, String name) {
        SearchEngine searchEngine = searchMap.get(strategy);
        return searchEngine.search(name);
    }

}
