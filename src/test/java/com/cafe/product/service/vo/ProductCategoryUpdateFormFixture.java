package com.cafe.product.service.vo;

public enum ProductCategoryUpdateFormFixture {
    STANDARD(
            Constants.ID,
            Constants.NAME
    ),
    ;

    private final Long productCategoryId;
    private final String name;

    ProductCategoryUpdateFormFixture(Long productCategoryId, String name) {
        this.productCategoryId = productCategoryId;
        this.name = name;
    }

    public ProductCategoryUpdateForm newInstance() {
        return new ProductCategoryUpdateForm(productCategoryId, name);
    }

    private static final class Constants {
        private static final Long ID = 1L;
        private static final String NAME = "nameUpdate";
    }

}
