package com.cafe.product.service.impl.info;

import com.cafe.product.service.vo.search.NameSearchResult;

public interface ProductInfoSearch {

    NameSearchResult searchComplete(String name);

    NameSearchResult searchChosung(String name);
}
