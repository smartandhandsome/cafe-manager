package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.service.impl.ProductInfoChanger;
import com.cafe.product.service.impl.ProductInfoCreator;
import com.cafe.product.service.impl.ProductInfoReader;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import com.cafe.product.service.vo.ProductPriceInfoUpdateForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;


@Component
@RequiredArgsConstructor
public class ProductInfoJpaRepositoryAdapter implements ProductInfoCreator, ProductInfoReader, ProductInfoChanger {

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
    public void change(ProductPriceInfoUpdateForm productPriceInfoUpdateForm) {
        ProductInfoJpaEntity entity = productInfoJpaRepository.findById(productPriceInfoUpdateForm.productInfoId())
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("해당 아이디와 일치하는 상품 정보가 존재하지 않습니다. [productInfoId: {0}]", productPriceInfoUpdateForm.productInfoId())
                ));
        entity.changePriceInfo(
                productPriceInfoUpdateForm.basePrice(),
                productPriceInfoUpdateForm.baseCost()
        );
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
