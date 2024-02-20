package com.cafe.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductCategoryIntegrationDeleter {

    private final ProductCategoryDeleter productCategoryDeleter;
    private final ProductInfoReader productInfoReader;
    private final ProductInfoIntegrationDeleter productInfoIntegrationDeleter;

    @Transactional
    public void deleteByProductCategoryId(Long productCategoryId) {
        List<Long> productInfoIds =  productInfoReader.readAllProductInfoIdByProductCategoryId(productCategoryId);
        productInfoIds.forEach(productInfoIntegrationDeleter::deleteByProductInfoId);
        productCategoryDeleter.deleteByProductCategoryId(productCategoryId);
    }

}
