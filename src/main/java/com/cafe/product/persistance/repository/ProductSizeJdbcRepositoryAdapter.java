package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductSizeJpaEntity;
import com.cafe.product.service.impl.ProductSizeCreator;
import com.cafe.product.service.vo.SizeRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSizeJdbcRepositoryAdapter implements ProductSizeCreator {

    private final ProductSizeJdbcRepository productSizeJdbcRepository;

    @Override
    public void createAll(
            ProductInfoJpaEntity productInfoJpaEntity,
            List<SizeRegistrationForm> sizeRegistrationForms
    ) {
        productSizeJdbcRepository.saveAll(
                convertToEntities(productInfoJpaEntity, sizeRegistrationForms)
        );
    }

    private List<ProductSizeJpaEntity> convertToEntities(
            ProductInfoJpaEntity productInfoJpaEntity,
            List<SizeRegistrationForm> sizeRegistrationForms
    ) {
        return sizeRegistrationForms.stream()
                .map(sizeRegistrationForm ->
                        convertToEntity(productInfoJpaEntity, sizeRegistrationForm)
                ).toList();
    }


    private ProductSizeJpaEntity convertToEntity(
            ProductInfoJpaEntity productInfoJpaEntity,
            SizeRegistrationForm sizeRegistrationForm
    ) {
        return ProductSizeJpaEntity.builder()
                .name(sizeRegistrationForm.name())
                .extraCharge(sizeRegistrationForm.extraCharge())
                .extraCost(sizeRegistrationForm.extraCost())
                .productInfoJpaEntity(productInfoJpaEntity)
                .build();
    }
}
