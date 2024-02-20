package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.service.impl.ProductInfoChanger;
import com.cafe.product.service.impl.ProductInfoCreator;
import com.cafe.product.service.impl.ProductInfoDeleter;
import com.cafe.product.service.impl.ProductInfoReader;
import com.cafe.product.service.vo.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ProductInfoJpaRepositoryAdapter implements ProductInfoCreator, ProductInfoReader, ProductInfoChanger, ProductInfoDeleter {

    private final ProductInfoJpaRepository productInfoJpaRepository;

    @Override
    public ProductInfoJpaEntity create(ProductInfoRegistrationForm productInfoRegistrationForm) {
        return productInfoJpaRepository.save(
                convertToEntity(productInfoRegistrationForm)
        );
    }

    @Override
    public boolean existsByBarcode(String barcode) {
        return productInfoJpaRepository.existsByBarcode(barcode);
    }

    @Override
    public boolean existsByBarcodeProductInfoIdNot(String barcode, Long productInfoId) {
        return productInfoJpaRepository.existsByBarcodeAndProductInfoIdNot(barcode, productInfoId);
    }

    @Override
    public void change(ProductPriceInfoUpdateForm productPriceInfoUpdateForm) {
        ProductInfoJpaEntity entity = getById(productPriceInfoUpdateForm.productInfoId());
        entity.changePriceInfo(
                productPriceInfoUpdateForm.basePrice(),
                productPriceInfoUpdateForm.baseCost()
        );
    }

    @Override
    public void change(ProductDetailInfoUpdateForm productDetailInfoUpdateForm) {
        ProductInfoJpaEntity entity = getById(productDetailInfoUpdateForm.productInfoId());
        entity.changeDetailInfo(
                productDetailInfoUpdateForm.name(),
                productDetailInfoUpdateForm.description(),
                productDetailInfoUpdateForm.barcode(),
                productDetailInfoUpdateForm.expirationDuration()
        );
    }

    @Override
    public List<Long> readAllProductInfoIdByProductCategoryId(Long productCategoryId) {
        return productInfoJpaRepository.findAllByProductCategoryId(productCategoryId);
    }

    @Override
    public void deleteByProductInfoId(Long productInfoId) {
        productInfoJpaRepository.deleteById(productInfoId);
    }

    private ProductInfoJpaEntity getById(Long productInfoId) {
        return productInfoJpaRepository.findById(productInfoId)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("해당 아이디와 일치하는 상품 정보가 존재하지 않습니다. [productInfoId: {0}]", productInfoId)
                ));
    }

    private ProductInfoJpaEntity convertToEntity(ProductInfoRegistrationForm domain) {
        return ProductInfoJpaEntity.builder()
                .productCategoryId(domain.productCategoryId())
                .basePrice(domain.basePrice())
                .baseCost(domain.baseCost())
                .name(domain.name())
                .description(domain.description())
                .barcode(domain.barcode())
                .expirationDuration(domain.expirationDuration())
                .build();
    }

}
