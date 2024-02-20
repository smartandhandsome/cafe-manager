package com.cafe.product.service.impl.size;

import com.cafe.product.service.vo.size.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.size.ProductSizePriceUpdateForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductSizeChanger {

    @Transactional
    void change(ProductSizePriceUpdateForm productSizePriceUpdateForm);

    @Transactional
    void change(ProductSizeInfoUpdateForm productSizeInfoUpdateForm);

}
