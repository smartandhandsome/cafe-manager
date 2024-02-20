package com.cafe.product.service.impl;

import com.cafe.product.persistance.dto.ProductSizeDetailViewDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductSizeReader {

    @Transactional(readOnly = true)
    List<ProductSizeDetailViewDto> readAllByProductInfoId(Long productInfoId);

}
