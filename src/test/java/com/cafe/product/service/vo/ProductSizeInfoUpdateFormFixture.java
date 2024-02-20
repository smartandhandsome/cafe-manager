package com.cafe.product.service.vo;

import com.cafe.product.service.vo.size.ProductSizeInfoUpdateForm;

public enum ProductSizeInfoUpdateFormFixture {
    STANDARD(Constants.ID, Constants.NAME),
    ;


    private final Long productSizeId;
    private final String name;

    ProductSizeInfoUpdateFormFixture(Long productSizeId, String name) {
        this.productSizeId = productSizeId;
        this.name = name;
    }

    public ProductSizeInfoUpdateForm newInstance() {
        return new ProductSizeInfoUpdateForm(productSizeId, name);
    }

    private static final class Constants {
        private static final Long ID = 1L;
        private static final String NAME = "name";
    }
}

