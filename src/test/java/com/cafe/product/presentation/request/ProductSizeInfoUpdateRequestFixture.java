package com.cafe.product.presentation.request;

public enum ProductSizeInfoUpdateRequestFixture {
    STANDARD(Constants.NAME),
    ;

    private final String name;


    ProductSizeInfoUpdateRequestFixture(String name) {
        this.name = name;
    }

    public ProductSizeInfoUpdateRequest newInstance() {
        return new ProductSizeInfoUpdateRequest(name);
    }

    private static final class Constants {
        private static final String NAME = "name";
    }
}
