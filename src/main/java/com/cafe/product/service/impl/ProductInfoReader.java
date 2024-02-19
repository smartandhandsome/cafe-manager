package com.cafe.product.service.impl;

public interface ProductInfoReader {
    boolean existsByBarcode(String barcode);

    boolean existsByBarcodeProductInfoIdNot(String barcode, Long productInfoId);
}
