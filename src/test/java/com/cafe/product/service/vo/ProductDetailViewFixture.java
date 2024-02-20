package com.cafe.product.service.vo;

import com.cafe.product.service.vo.size.ProductSizeDetailView;

import java.util.List;
import java.util.stream.Collectors;

public enum ProductDetailViewFixture {
    STANDARD(
            Constants.CATEGORY_ID,
            Constants.CATEGORY_NAME,
            Constants.PRODUCT_INFO_ID,
            Constants.BASE_PRICE,
            Constants.BASE_COST,
            Constants.PRODUCT_NAME,
            Constants.DESCRIPTION,
            Constants.BARCODE,
            Constants.EXPIRATION_DURATION
    ),
    ;

    private final Long categoryId;
    private final String categoryName;
    private final Long productInfoId;
    private final int basePrice;
    private final int baseCost;
    private final String productName;
    private final String description;
    private final String barcode;
    private final String expirationDuration;

    ProductDetailViewFixture(
            Long categoryId,
            String categoryName,
            Long productInfoId,
            int basePrice,
            int baseCost,
            String productName,
            String description,
            String barcode,
            String expirationDuration
    ) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productInfoId = productInfoId;
        this.basePrice = basePrice;
        this.baseCost = baseCost;
        this.productName = productName;
        this.description = description;
        this.barcode = barcode;
        this.expirationDuration = expirationDuration;
    }

    public ProductDetailView newInstance(
            List<ProductSizeDetailView> productSizeDetailViewList
    ) {
        return ProductDetailView.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .productInfoId(productInfoId)
                .basePrice(basePrice)
                .baseCost(baseCost)
                .productName(productName)
                .description(description)
                .barcode(barcode)
                .expirationDuration(expirationDuration)
                .productSizeDetailViewList(productSizeDetailViewList)
                .build();
    }

    private static final class Constants {
        private static final Long CATEGORY_ID = 1L;
        private static final String CATEGORY_NAME = "categoryName";
        private static final Long PRODUCT_INFO_ID = 100L;
        private static final int BASE_PRICE = 500;
        private static final int BASE_COST = 300;
        private static final String PRODUCT_NAME = "productName";
        private static final String DESCRIPTION = "description";
        private static final String BARCODE = "123456789";
        private static final String EXPIRATION_DURATION = "expirationDuration";
    }
}
