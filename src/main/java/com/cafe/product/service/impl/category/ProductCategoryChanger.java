package com.cafe.product.service.impl.category;

import com.cafe.product.service.vo.cateory.ProductCategoryUpdateForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductCategoryChanger {

    @Transactional
    void change(ProductCategoryUpdateForm productCategoryUpdateForm);

}
