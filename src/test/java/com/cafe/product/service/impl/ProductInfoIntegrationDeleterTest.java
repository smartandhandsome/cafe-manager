package com.cafe.product.service.impl;

import com.cafe.product.service.impl.info.ProductInfoDeleter;
import com.cafe.product.service.impl.info.ProductInfoIntegrationDeleter;
import com.cafe.product.service.impl.size.ProductSizeDeleter;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductInfoIntegrationDeleterTest {

    @InjectMocks
    ProductInfoIntegrationDeleter productInfoIntegrationDeleter;
    @Mock
    ProductInfoDeleter productInfoDeleter;
    @Mock
    ProductSizeDeleter productSizeDeleter;

    @Test
    void 상품_정보와_연관된_정보를_모두_삭제할_수_있다() {
        // given
        Long productInfoId = 1L;

        // when
        productInfoIntegrationDeleter.deleteByProductInfoId(productInfoId);

        // then
        then(productSizeDeleter).should(times(1)).deleteAllByProductInfoId(productInfoId);
        then(productInfoDeleter).should(times(1)).deleteByProductInfoId(productInfoId);
    }

}
