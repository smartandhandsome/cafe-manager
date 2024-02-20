package com.cafe.product.service.impl.info;

import com.cafe.product.service.vo.info.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.info.ProductPriceInfoUpdateForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductInfoChanger {

    @Transactional
    void change(ProductPriceInfoUpdateForm productPriceInfoUpdateForm);

    @Transactional
    void change(ProductDetailInfoUpdateForm productDetailInfoUpdateForm);

}
