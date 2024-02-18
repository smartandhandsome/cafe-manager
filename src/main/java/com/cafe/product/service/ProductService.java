package com.cafe.product.service;

import com.cafe.product.service.impl.ProductRegister;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.SizeRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRegister productRegister;

    public void register(
            ProductInfoRegistrationForm productInfoRegistrationForm,
            List<SizeRegistrationForm> sizeRegistrationFormList
    ) {
        productRegister.register(
                productInfoRegistrationForm,
                sizeRegistrationFormList
        );
    }

}
