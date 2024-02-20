package com.cafe.product.persistance.dto;

import com.cafe.product.service.vo.ProductListView;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductListViewDto {
    private Long productInfoId;
    private Long categoryId;
    private String categoryName;
    private String productName;
    private int basePrice;



    public ProductListView toProductListView() {
        return new ProductListView(
                productInfoId,
                basePrice,
                productName,
                categoryId,
                categoryName
        );
    }

}
