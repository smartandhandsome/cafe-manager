package com.cafe.product.service;

import com.cafe.product.persistance.dto.ProductListViewDto;
import com.cafe.product.service.impl.ProductInfoReader;
import com.cafe.product.service.impl.ProductIntegrationReader;
import com.cafe.product.service.vo.ProductDetailView;
import com.cafe.product.service.vo.ProductListView;
import com.cafe.product.service.vo.ProductListViewList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

    private static final int PAGE_SIZE = 10;

    private final ProductInfoReader productInfoReader;
    private final ProductIntegrationReader productIntegrationReader;

    public ProductListViewList queryProductList(Long lastProductId) {
        List<ProductListViewDto> dtoList = productInfoReader.readProductListViewPagination(lastProductId, PAGE_SIZE);
        boolean hasNext = productInfoReader.hasProductInfoIdGreaterThan(lastProductId + PAGE_SIZE);

        List<ProductListView> productListViewList = dtoList.stream()
                .map(ProductListViewDto::toProductListView)
                .toList();
        return new ProductListViewList(productListViewList, hasNext);
    }

    public ProductDetailView queryProductDetail(Long productInfoId) {
        return productIntegrationReader.readDetail(productInfoId);
    }
}
