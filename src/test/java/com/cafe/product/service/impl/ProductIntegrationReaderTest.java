package com.cafe.product.service.impl;

import com.cafe.product.persistance.dto.ProductInfoCategoryDetailViewDto;
import com.cafe.product.persistance.dto.ProductInfoCategoryDetailViewDtoFixture;
import com.cafe.product.persistance.dto.ProductSizeDetailViewDto;
import com.cafe.product.persistance.dto.ProductSizeDetailViewDtoFixture;
import com.cafe.product.service.impl.info.ProductInfoReader;
import com.cafe.product.service.impl.size.ProductSizeReader;
import com.cafe.product.service.vo.ProductDetailView;
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
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductIntegrationReaderTest {

    @InjectMocks
    ProductIntegrationReader productIntegrationReader;
    @Mock
    ProductInfoReader productInfoReader;
    @Mock
    ProductSizeReader productSizeReader;

    @Test
    void 상품_상세_정보를_가져올_수_있다() {
        // given
        ProductInfoCategoryDetailViewDto productInfoCategoryDetailViewDto
                = ProductInfoCategoryDetailViewDtoFixture.STANDARD.newInstance();
        List<ProductSizeDetailViewDto> productSizeDetailViewDtos =
                List.of(
                        ProductSizeDetailViewDtoFixture.SMALL.newInstance(),
                        ProductSizeDetailViewDtoFixture.LARGE.newInstance()
                );
        ProductDetailView expectedDetailView = ProductDetailView.of(productInfoCategoryDetailViewDto, productSizeDetailViewDtos);

        Long productInfoId = expectedDetailView.productInfoId();

        given(productInfoReader.readProductDetail(productInfoId)).willReturn(productInfoCategoryDetailViewDto);
        given(productSizeReader.readAllByProductInfoId(productInfoId)).willReturn(productSizeDetailViewDtos);

        // when
        ProductDetailView detailView = productIntegrationReader.readDetail(productInfoId);

        // then
        assertThat(detailView).usingRecursiveComparison()
                .isEqualTo(expectedDetailView);
    }

}
