package com.cafe.product.service;

import com.cafe.product.service.impl.ProductCategoryChanger;
import com.cafe.product.service.impl.ProductCategoryCreator;
import com.cafe.product.service.impl.ProductCategoryDuplicationValidator;
import com.cafe.product.service.impl.ProductCategoryIntegrationDeleter;
import com.cafe.product.service.impl.ProductInfoChanger;
import com.cafe.product.service.impl.ProductInfoDuplicationValidator;
import com.cafe.product.service.impl.ProductInfoIntegrationCreator;
import com.cafe.product.service.impl.ProductInfoIntegrationDeleter;
import com.cafe.product.service.impl.ProductSizeChanger;
import com.cafe.product.service.impl.ProductSizeDeleter;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductCategoryUpdateForm;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import com.cafe.product.service.vo.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.ProductSizePriceUpdateForm;
import com.cafe.product.service.vo.SizeRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCommandService {

    private final ProductCategoryDuplicationValidator productCategoryDuplicationValidator;
    private final ProductInfoDuplicationValidator productInfoDuplicationValidator;

    private final ProductInfoIntegrationCreator productInfoIntegrationCreator;
    private final ProductCategoryCreator productCategoryCreator;

    private final ProductInfoChanger productInfoChanger;
    private final ProductCategoryChanger productCategoryChanger;
    private final ProductSizeChanger productSizeChanger;

    private final ProductCategoryIntegrationDeleter productCategoryIntegrationDeleter;
    private final ProductInfoIntegrationDeleter productInfoIntegrationDeleter;
    private final ProductSizeDeleter productSizeDeleter;

    public void register(ProductCategoryRegistrationForm productCategoryRegistrationForm) {
        productCategoryDuplicationValidator.validate(productCategoryRegistrationForm.name());
        productCategoryCreator.create(productCategoryRegistrationForm);
    }

    public void register(
            ProductInfoRegistrationForm productInfoRegistrationForm,
            List<SizeRegistrationForm> sizeRegistrationFormList
    ) {
        productInfoDuplicationValidator.validate(productInfoRegistrationForm);
        productInfoIntegrationCreator.create(
                productInfoRegistrationForm,
                sizeRegistrationFormList
        );
    }

    public void update(ProductPriceInfoUpdateForm productPriceInfoUpdateForm) {
        productInfoChanger.change(productPriceInfoUpdateForm);
    }

    public void update(ProductDetailInfoUpdateForm productDetailInfoUpdateForm) {
        productInfoDuplicationValidator.validate(productDetailInfoUpdateForm);
        productInfoChanger.change(productDetailInfoUpdateForm);
    }

    public void update(ProductCategoryUpdateForm productCategoryUpdateForm) {
        productCategoryDuplicationValidator.validate(productCategoryUpdateForm.name());
        productCategoryChanger.change(productCategoryUpdateForm);
    }

    public void update(ProductSizePriceUpdateForm productSizePriceUpdateForm) {
        productSizeChanger.change(productSizePriceUpdateForm);
    }

    public void update(ProductSizeInfoUpdateForm productSizeInfoUpdateForm) {
        productSizeChanger.change(productSizeInfoUpdateForm);
    }

    public void deleteProductSize(Long productSizeId) {
        productSizeDeleter.deleteByProductSizeId(productSizeId);
    }

    public void deleteProductInfo(Long productInfoId) {
        productInfoIntegrationDeleter.deleteByProductInfoId(productInfoId);
    }

    public void deleteProductCategory(Long productCategoryId) {
        productCategoryIntegrationDeleter.deleteByProductCategoryId(productCategoryId);
    }

}
