package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.service.impl.ProductInfoCreator;
import com.cafe.product.service.vo.ProductInfoRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductInfoJpaRepositoryAdapter implements ProductInfoCreator {

    private final ProductInfoJpaRepository productInfoJpaRepository;

    @Override
    public ProductInfoJpaEntity create(ProductInfoRegistrationForm productInfoRegistrationForm) {
        return productInfoJpaRepository.save(
                convertToEntity(productInfoRegistrationForm)
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
