package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.SizeRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductInfoIntegrationCreator {

    private final ProductInfoCreator productInfoCreator;
    private final ProductSizeCreator productSizeCreator;

    @Transactional
    public void create(ProductInfoRegistrationForm productInfoRegistrationForm, List<SizeRegistrationForm> sizeRegistrationFormList) {
        Long productInfoId = productInfoCreator.create(productInfoRegistrationForm);
        productSizeCreator.createAll(productInfoId, sizeRegistrationFormList);
    }

}
