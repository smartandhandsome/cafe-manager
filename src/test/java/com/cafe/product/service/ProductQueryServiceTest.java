package com.cafe.product.service;

import com.cafe.product.persistance.dto.ProductListViewDto;
import com.cafe.product.service.impl.info.ProductInfoReader;
import com.cafe.product.service.vo.ProductListView;
import com.cafe.product.service.vo.ProductListViewList;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductQueryServiceTest {

    @InjectMocks
    ProductQueryService productQueryService;
    @Mock
    ProductInfoReader productInfoReader;

    @Test
    void 상품_리스트를_가져올_수_있다() {
        // given
        Long lastProductId = 1L;
        int pageSize = 10;
        Long currentPageLastProductId = lastProductId + pageSize;
        List<ProductListViewDto> dtoList = List.of();
        boolean hasNext = false;

        given(productInfoReader.readProductListViewPagination(lastProductId, pageSize)).willReturn(dtoList);
        given(productInfoReader.hasProductInfoIdGreaterThan(lastProductId + pageSize)).willReturn(hasNext);

        List<ProductListView> productListViewList = dtoList.stream()
                .map(ProductListViewDto::toProductListView)
                .toList();
        ProductListViewList expectedViewList = new ProductListViewList(productListViewList, hasNext);

        // when
        ProductListViewList viewList = productQueryService.queryProductList(lastProductId);

        // then
        assertThat(viewList).usingRecursiveComparison()
                .isEqualTo(expectedViewList);
    }

}
