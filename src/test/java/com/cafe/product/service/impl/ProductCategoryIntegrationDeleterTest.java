package com.cafe.product.service.impl;

import com.cafe.product.service.impl.category.ProductCategoryDeleter;
import com.cafe.product.service.impl.category.ProductCategoryIntegrationDeleter;
import com.cafe.product.service.impl.info.ProductInfoIntegrationDeleter;
import com.cafe.product.service.impl.info.ProductInfoReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductCategoryIntegrationDeleterTest {

    @InjectMocks
    ProductCategoryIntegrationDeleter productCategoryIntegrationDeleter;
    @Mock
    ProductCategoryDeleter productCategoryDeleter;
    @Mock
    ProductInfoReader productInfoReader;
    @Mock
    ProductInfoIntegrationDeleter productInfoIntegrationDeleter;

    @Test
    void 상품_카테고리와_연관된_모든_데이터와_카테고리를_삭제할_수_있다() {
        // given
        Long productCategoryId = 1L;
        List<Long> productInfoIds = List.of(1L, 2L, 3L);

        given(productInfoReader.readAllProductInfoIdByProductCategoryId(productCategoryId)).willReturn(productInfoIds);

        // when
        productCategoryIntegrationDeleter.deleteByProductCategoryId(productCategoryId);

        // then
        then(productInfoIntegrationDeleter).should(atLeastOnce()).deleteByProductInfoId(anyLong());
        then(productCategoryDeleter).should(times(1)).deleteByProductCategoryId(productCategoryId);
    }

}
