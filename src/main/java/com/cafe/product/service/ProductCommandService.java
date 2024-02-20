package com.cafe.product.service;

import com.cafe.product.service.impl.category.ProductCategoryChanger;
import com.cafe.product.service.impl.category.ProductCategoryCreator;
import com.cafe.product.service.impl.category.ProductCategoryDuplicationValidator;
import com.cafe.product.service.impl.category.ProductCategoryIntegrationDeleter;
import com.cafe.product.service.impl.info.ProductInfoChanger;
import com.cafe.product.service.impl.info.ProductInfoDuplicationValidator;
import com.cafe.product.service.impl.info.ProductInfoIntegrationCreator;
import com.cafe.product.service.impl.info.ProductInfoIntegrationDeleter;
import com.cafe.product.service.impl.info.ProductInfoPreprocessor;
import com.cafe.product.service.impl.size.ProductSizeChanger;
import com.cafe.product.service.impl.size.ProductSizeDeleter;
import com.cafe.product.service.vo.cateory.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.cateory.ProductCategoryUpdateForm;
import com.cafe.product.service.vo.info.PreprocessedProductInfoRegistrationForm;
import com.cafe.product.service.vo.info.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.info.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.info.ProductPriceInfoUpdateForm;
import com.cafe.product.service.vo.size.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.size.ProductSizePriceUpdateForm;
import com.cafe.product.service.vo.size.SizeRegistrationForm;
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

    private final ProductInfoPreprocessor productInfoPreprocessor;

    public void register(ProductCategoryRegistrationForm productCategoryRegistrationForm) {
        productCategoryDuplicationValidator.validate(productCategoryRegistrationForm.name());
        productCategoryCreator.create(productCategoryRegistrationForm);
    }

    public void register(
            ProductInfoRegistrationForm form,
            List<SizeRegistrationForm> sizeRegistrationFormList
    ) {
        productInfoDuplicationValidator.validate(form);
        PreprocessedProductInfoRegistrationForm preprocessedForm
                = productInfoPreprocessor.preprocess(form);

        productInfoIntegrationCreator.create(preprocessedForm, sizeRegistrationFormList);
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
