package com.cafe.product.presentation.request;

public enum ProductCategoryRegistrationRequestFixture {
    STANDARD(
            Constants.NAME
    ),
    ;

    private final String name;

    ProductCategoryRegistrationRequestFixture(String name) {
        this.name = name;
    }

    public ProductCategoryRegistrationRequest newInstance() {
        return new ProductCategoryRegistrationRequest(name);
    }

    private static final class Constants {
        private static final String NAME = "name";
    }
}
