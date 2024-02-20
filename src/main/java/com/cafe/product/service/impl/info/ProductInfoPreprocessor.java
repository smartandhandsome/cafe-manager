package com.cafe.product.service.impl.info;

import com.cafe.product.service.vo.info.PreprocessedProductInfoRegistrationForm;
import com.cafe.product.service.vo.info.ProductInfoRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductInfoPreprocessor {

    public final WordToChosungConverter wordToChosungConverter;

    public PreprocessedProductInfoRegistrationForm preprocess(ProductInfoRegistrationForm productInfoRegistrationForm) {
        String chosung = wordToChosungConverter.convert(productInfoRegistrationForm.name());
        return PreprocessedProductInfoRegistrationForm.of(
                productInfoRegistrationForm,
                chosung
        );
    }

}
