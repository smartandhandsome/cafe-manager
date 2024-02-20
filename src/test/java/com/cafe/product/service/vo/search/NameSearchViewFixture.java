package com.cafe.product.service.vo.search;

public enum NameSearchViewFixture {
    LATTE(1L, "라떼"),
    ICE_LATTE(2L, "아이스 라떼");

    private final Long productInfoId;
    private final String name;

    NameSearchViewFixture(Long productInfoId, String name) {
        this.productInfoId = productInfoId;
        this.name = name;
    }

    public NameSearchView newInstance() {
        return new NameSearchView(productInfoId, name);
    }
}
