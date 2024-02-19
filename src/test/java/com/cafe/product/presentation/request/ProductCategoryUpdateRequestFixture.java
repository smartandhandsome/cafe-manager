package com.cafe.product.presentation.request;

public enum ProductCategoryUpdateRequestFixture {
    STANDARD(Constants.NAME),
    ;

    private final String name;

    ProductCategoryUpdateRequestFixture(String name) {
        this.name = name;
    }

    public ProductCategoryUpdateRequest newInstance() {
        return new ProductCategoryUpdateRequest(name);
    }

    private static final class Constants {
        private static final String NAME = "name";
    }

}
