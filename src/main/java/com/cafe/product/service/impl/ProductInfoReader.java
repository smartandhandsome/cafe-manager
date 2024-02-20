package com.cafe.product.service.impl;

import com.cafe.product.persistance.dto.ProductListViewDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductInfoReader {

    @Transactional(readOnly = true)
    boolean existsByBarcode(String barcode);

    @Transactional(readOnly = true)
    boolean existsByBarcodeProductInfoIdNot(String barcode, Long productInfoId);

    @Transactional(readOnly = true)
    List<Long> readAllProductInfoIdByProductCategoryId(Long productCategoryId);

    @Transactional(readOnly = true)
    List<ProductListViewDto> readProductListViewPagination(Long productListCursorId, int pageSize);

    @Transactional(readOnly = true)
    boolean hasProductInfoIdGreaterThan(Long productInfoId);
}
