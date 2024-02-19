package com.cafe.product.service.impl;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.SizeRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductRegister {

    private final ProductInfoCreator productInfoCreator;
    private final ProductSizeCreator productSizeCreator;
    private final ProductCategoryCreator productCategoryCreator;

    @Transactional
    public void register(
            ProductInfoRegistrationForm productInfoRegistrationForm,
            List<SizeRegistrationForm> sizeRegistrationFormList
    ) {
        ProductInfoJpaEntity productInfoJpaEntity = productInfoCreator.create(productInfoRegistrationForm);
        productSizeCreator.createAll(productInfoJpaEntity, sizeRegistrationFormList);
    }

    @Transactional
    public void register(ProductCategoryRegistrationForm productInfoRegistrationForm) {
        productCategoryCreator.create(productInfoRegistrationForm);
    }

}
