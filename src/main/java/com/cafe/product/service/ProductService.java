package com.cafe.product.service;

import com.cafe.product.service.impl.ProductRegister;
import com.cafe.product.service.impl.ProductValidator;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.SizeRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRegister productRegister;
    private final ProductValidator productValidator;

    public void register(ProductCategoryRegistrationForm productCategoryRegistrationForm) {
        productValidator.validate(productCategoryRegistrationForm);
        productRegister.register(productCategoryRegistrationForm);
    }

    public void register(
            ProductInfoRegistrationForm productInfoRegistrationForm,
            List<SizeRegistrationForm> sizeRegistrationFormList
    ) {
        productValidator.validate(productInfoRegistrationForm);
        productRegister.register(
                productInfoRegistrationForm,
                sizeRegistrationFormList
        );
    }

}
