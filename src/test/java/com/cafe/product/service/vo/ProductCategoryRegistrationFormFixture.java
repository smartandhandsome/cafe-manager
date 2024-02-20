package com.cafe.product.service.vo;

import com.cafe.product.service.vo.cateory.ProductCategoryRegistrationForm;

public enum ProductCategoryRegistrationFormFixture {
    STANDARD(Constants.NAME),
    ;

    private final String name;

    ProductCategoryRegistrationFormFixture(String name) {
        this.name = name;
    }

    public ProductCategoryRegistrationForm newInstance() {
        return new ProductCategoryRegistrationForm(name);
    }

    private static final class Constants {
        private final static String NAME = "name";
    }
}
