package com.cafe.product.service.impl.category;

import com.cafe.product.service.vo.cateory.ProductCategoryRegistrationForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductCategoryCreator {

    @Transactional
    void create(ProductCategoryRegistrationForm productInfoRegistrationForm);

}
