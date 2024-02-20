package com.cafe.product.service.impl;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductInfoReader {

    @Transactional(readOnly = true)
    boolean existsByBarcode(String barcode);

    @Transactional(readOnly = true)
    boolean existsByBarcodeProductInfoIdNot(String barcode, Long productInfoId);

    @Transactional(readOnly = true)
    List<Long> readAllProductInfoIdByProductCategoryId(Long productCategoryId);
}
