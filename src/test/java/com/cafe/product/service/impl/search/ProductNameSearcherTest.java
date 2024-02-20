package com.cafe.product.service.impl.search;

import com.cafe.product.service.vo.search.NameSearchResult;
import com.cafe.product.service.vo.search.NameSearchStrategy;
import com.cafe.product.service.vo.search.NameSearchViewFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductNameSearcherTest {

    ProductNameSearcher productNameSearcher;

    @Mock
    private SearchEngine searchEngine;

    String name;

    NameSearchStrategy strategy;

    @BeforeEach
    void setUp() {
        name = "라떼";
        strategy = NameSearchStrategy.determine(name);
        given(searchEngine.getStrategy()).willReturn(strategy);
        productNameSearcher = new ProductNameSearcher(Set.of(searchEngine));
    }

    @Test
    void 전략에_따라_찾기를_할_수_있다() {
        // given
        NameSearchResult expectedNameSearchResult = new NameSearchResult(
                List.of(NameSearchViewFixture.LATTE.newInstance(), NameSearchViewFixture.ICE_LATTE.newInstance())
        );

        given(searchEngine.search(name)).willReturn(expectedNameSearchResult);

        // when
        NameSearchResult nameSearchResult = productNameSearcher.search(strategy, name);

        // then
        assertThat(nameSearchResult).usingRecursiveComparison()
                .isEqualTo(expectedNameSearchResult);
    }

}
