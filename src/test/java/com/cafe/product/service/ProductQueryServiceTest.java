package com.cafe.product.service;

import com.cafe.product.persistance.dto.ProductListViewDto;
import com.cafe.product.service.impl.ProductIntegrationReader;
import com.cafe.product.service.impl.info.ProductInfoReader;
import com.cafe.product.service.impl.search.ProductNameSearcher;
import com.cafe.product.service.vo.ProductDetailView;
import com.cafe.product.service.vo.ProductDetailViewFixture;
import com.cafe.product.service.vo.ProductListView;
import com.cafe.product.service.vo.ProductListViewList;
import com.cafe.product.service.vo.search.NameSearchResult;
import com.cafe.product.service.vo.search.NameSearchStrategy;
import com.cafe.product.service.vo.search.NameSearchViewFixture;
import com.cafe.product.service.vo.size.ProductSizeDetailViewFixture;
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
    @Mock
    ProductIntegrationReader productIntegrationReader;
    @Mock
    ProductNameSearcher productNameSearcher;

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

    @Test
    void 상품_상세_정보를_가져올_수_있다() {
        // given
        ProductDetailView expectedDetailView = ProductDetailViewFixture.STANDARD.newInstance(
                List.of(ProductSizeDetailViewFixture.SMALL.newInstance(),
                        ProductSizeDetailViewFixture.LARGE.newInstance())
        );

        Long productInfoId = expectedDetailView.productInfoId();

        given(productIntegrationReader.readDetail(productInfoId)).willReturn(expectedDetailView);

        // when
        ProductDetailView detailView = productQueryService.queryProductDetail(productInfoId);

        // then
        assertThat(detailView).usingRecursiveComparison()
                .isEqualTo(expectedDetailView);
    }

    @Test
    void 이름으로_검색할_수_있다() {
        // given
        String name = "라떼";

        NameSearchStrategy strategy = NameSearchStrategy.determine(name);
        NameSearchResult expextedNameSearchResult = new NameSearchResult(
                List.of(
                        NameSearchViewFixture.LATTE.newInstance(),
                        NameSearchViewFixture.ICE_LATTE.newInstance()
                )
        );

        given(productNameSearcher.search(strategy, name)).willReturn(expextedNameSearchResult);

        // when
        NameSearchResult nameSearchResult = productNameSearcher.search(strategy, name);

        // then
        assertThat(nameSearchResult).usingRecursiveComparison()
                .isEqualTo(expextedNameSearchResult);
    }

}
