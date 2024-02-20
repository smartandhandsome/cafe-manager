package com.cafe.product.service.impl;

import com.cafe.product.persistance.dto.ProductInfoCategoryDetailViewDto;
import com.cafe.product.persistance.dto.ProductSizeDetailViewDto;
import com.cafe.product.service.impl.info.ProductInfoReader;
import com.cafe.product.service.impl.size.ProductSizeReader;
import com.cafe.product.service.vo.ProductDetailView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductIntegrationReader {

    private final ProductInfoReader productInfoReader;
    private final ProductSizeReader productSizeReader;

    @Transactional(readOnly = true)
    public ProductDetailView readDetail(Long productInfoId) {
        ProductInfoCategoryDetailViewDto productInfoCategoryDetailViewDto
                = productInfoReader.readProductDetail(productInfoId);
        List<ProductSizeDetailViewDto> productSizeDetailViewDtos =
                productSizeReader.readAllByProductInfoId(productInfoId);
        return ProductDetailView.of(productInfoCategoryDetailViewDto, productSizeDetailViewDtos);
    }

}
