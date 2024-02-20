package com.cafe.product.service.impl.info;

import com.cafe.product.service.vo.info.PreprocessedProductInfoRegistrationForm;
import org.springframework.transaction.annotation.Transactional;

public interface ProductInfoCreator {

    @Transactional
    Long create(PreprocessedProductInfoRegistrationForm form);

}
