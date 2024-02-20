package com.cafe.product.service.impl.search;


import com.cafe.product.service.vo.search.NameSearchResult;
import com.cafe.product.service.vo.search.NameSearchStrategy;

public interface SearchEngine {

    NameSearchResult search(String name);

    NameSearchStrategy getStrategy();

}
