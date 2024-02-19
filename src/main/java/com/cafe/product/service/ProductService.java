package com.cafe.product.service;

import com.cafe.product.service.impl.ProductChanger;
import com.cafe.product.service.impl.ProductRegister;
import com.cafe.product.service.impl.ProductValidator;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductCategoryUpdateForm;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import com.cafe.product.service.vo.SizeRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRegister productRegister;
    private final ProductValidator productValidator;
    private final ProductChanger productChanger;

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

    public void update(ProductPriceInfoUpdateForm productPriceInfoUpdateForm) {
        productChanger.change(productPriceInfoUpdateForm);
    }

    public void update(ProductDetailInfoUpdateForm productDetailInfoUpdateForm) {
        productValidator.validate(productDetailInfoUpdateForm);
        productChanger.change(productDetailInfoUpdateForm);
    }

    public void update(ProductCategoryUpdateForm productCategoryUpdateForm) {
        productValidator.validate(productCategoryUpdateForm);
        productChanger.change(productCategoryUpdateForm);
    }
}
