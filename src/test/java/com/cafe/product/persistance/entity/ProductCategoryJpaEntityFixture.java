package com.cafe.product.persistance.entity;

import com.cafe.common.util.EntityIdInjector;

public enum ProductCategoryJpaEntityFixture {
    STANDARD(
            Constants.ID,
            Constants.NAME
    ),
    NOT_EXISTED(
            Constants.NULL_ID,
            Constants.NOT_EXISTED_NAME
    ),
    ;

    private final Long productCategoryId;
    private final String name;

    ProductCategoryJpaEntityFixture(Long productCategoryId, String name) {
        this.productCategoryId = productCategoryId;
        this.name = name;
    }

    public ProductCategoryJpaEntity newInstance() {
        ProductCategoryJpaEntity entity = new ProductCategoryJpaEntity(name);
        EntityIdInjector.inject(entity, "productCategoryId", productCategoryId);
        return entity;
    }

    private static final class Constants {
        private static final Long ID = 1L;
        private static final Long NULL_ID = null;
        private static final String NAME = "name";
        private static final String NOT_EXISTED_NAME = "notExistedName";
    }
}
