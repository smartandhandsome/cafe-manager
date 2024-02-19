package com.cafe.product.service.impl;

import com.cafe.common.exception.DuplicatedResourceException;
import com.cafe.common.exception.ErrorCode;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class ProductInfoDuplicationValidator {
    private final ProductInfoReader productInfoReader;

    public void validate(ProductInfoRegistrationForm productInfoRegistrationForm) {
        if (productInfoReader.existsByBarcode(productInfoRegistrationForm.barcode())) {
            throw new DuplicatedResourceException(
                    ErrorCode.DUPLICATED_BARCODE,
                    MessageFormat.format("중복된 상품 바코드 [barcode: {0}]", productInfoRegistrationForm.barcode())
            );
        }
    }
}
