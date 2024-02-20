package com.cafe.product.service.impl.size;

import com.cafe.product.service.vo.size.SizeRegistrationForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductSizeCreator {

    @Transactional
    void createAll(Long productInfoId, List<SizeRegistrationForm> sizeRegistrationForms);

}
