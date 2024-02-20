package com.cafe.product.service.impl.info;

import com.cafe.product.service.impl.size.ProductSizeCreator;
import com.cafe.product.service.vo.info.PreprocessedProductInfoRegistrationForm;
import com.cafe.product.service.vo.size.SizeRegistrationForm;
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
    public void create(PreprocessedProductInfoRegistrationForm form, List<SizeRegistrationForm> sizeFormList) {
        Long productInfoId = productInfoCreator.create(form);
        productSizeCreator.createAll(productInfoId, sizeFormList);
    }

}
