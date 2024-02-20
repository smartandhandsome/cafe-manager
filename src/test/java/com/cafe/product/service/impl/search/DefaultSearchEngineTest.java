package com.cafe.product.service.impl.search;

import com.cafe.product.service.impl.info.ProductInfoSearch;
import com.cafe.product.service.vo.search.NameSearchResult;
import com.cafe.product.service.vo.search.NameSearchStrategy;
import com.cafe.product.service.vo.search.NameSearchViewFixture;
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
class DefaultSearchEngineTest {

    @InjectMocks
    DefaultSearchEngine defaultSearchEngine;

    @Mock
    ProductInfoSearch productInfoSearch;

    @Test
    void 기본_검색을_할_수_있다() {
        // given
        String name = "라떼";

        NameSearchResult expectedNameSearchResult = new NameSearchResult(List.of(NameSearchViewFixture.LATTE.newInstance(),
                NameSearchViewFixture.ICE_LATTE.newInstance()));

        given(productInfoSearch.searchComplete(name)).willReturn(expectedNameSearchResult);

        // when
        NameSearchResult nameSearchResult = defaultSearchEngine.search(name);

        // then
        assertThat(nameSearchResult).usingRecursiveComparison()
                .isEqualTo(expectedNameSearchResult);
    }

    @Test
    void 전략을_반환할_수_있다() {
        // given

        // when
        NameSearchStrategy strategy = defaultSearchEngine.getStrategy();

        // then
        assertThat(strategy).isEqualTo(NameSearchStrategy.DEFAULT);
    }

}
