package com.cafe.product.service.impl;

import com.cafe.product.service.vo.ProductCategoryUpdateForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductCategoryChanger {
    @Transactional
    void change(ProductCategoryUpdateForm productCategoryUpdateForm);
}
