package com.cafe.product.service.impl.info;

import com.cafe.product.service.impl.size.ProductSizeDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductInfoIntegrationDeleter {

    private final ProductInfoDeleter productInfoDeleter;
    private final ProductSizeDeleter productSizeDeleter;

    @Transactional
    public void deleteByProductInfoId(Long productInfoId) {
        productSizeDeleter.deleteAllByProductInfoId(productInfoId);
        productInfoDeleter.deleteByProductInfoId(productInfoId);
    }

}
