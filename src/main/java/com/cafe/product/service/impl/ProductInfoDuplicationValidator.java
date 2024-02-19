package com.cafe.product.service.impl;

import com.cafe.common.exception.DuplicatedResourceException;
import com.cafe.common.exception.ErrorCode;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class ProductInfoDuplicationValidator {
    private static final String DUPLICATED_BARCODE_EXCETION_MESSAGE = "중복된 상품 바코드 [barcode: {0}]";

    private final ProductInfoReader productInfoReader;

    public void validate(ProductInfoRegistrationForm productInfoRegistrationForm) {
        if (productInfoReader.existsByBarcode(productInfoRegistrationForm.barcode())) {
            throw new DuplicatedResourceException(
                    ErrorCode.DUPLICATED_BARCODE,
                    MessageFormat.format(DUPLICATED_BARCODE_EXCETION_MESSAGE, productInfoRegistrationForm.barcode())
            );
        }
    }

    public void validate(ProductDetailInfoUpdateForm productDetailInfoUpdateForm) {
        if (productInfoReader.existsByBarcodeProductInfoIdNot(
                productDetailInfoUpdateForm.barcode(),
                productDetailInfoUpdateForm.productInfoId()
        )) {
            throw new DuplicatedResourceException(
                    ErrorCode.DUPLICATED_BARCODE,
                    MessageFormat.format(DUPLICATED_BARCODE_EXCETION_MESSAGE, productDetailInfoUpdateForm.barcode())
            );
        }
    }
}
