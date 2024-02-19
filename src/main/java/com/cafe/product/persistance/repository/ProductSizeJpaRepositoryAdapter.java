package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.persistance.entity.ProductSizeJpaEntity;
import com.cafe.product.service.impl.ProductSizeChanger;
import com.cafe.product.service.impl.ProductSizeCreator;
import com.cafe.product.service.vo.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.ProductSizePriceUpdateForm;
import com.cafe.product.service.vo.SizeRegistrationForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSizeJpaRepositoryAdapter implements ProductSizeCreator, ProductSizeChanger {

    private final ProductSizeJpaRepository productSizeJpaRepository;
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

    @Override
    public void change(ProductSizePriceUpdateForm productSizePriceUpdateForm) {
        ProductSizeJpaEntity entity = getEntity(productSizePriceUpdateForm.productSizeId());
        entity.changePriceInfo(
                productSizePriceUpdateForm.extraCharge(),
                productSizePriceUpdateForm.extraCost()
        );
    }

    @Override
    public void change(ProductSizeInfoUpdateForm productSizeInfoUpdateForm) {
        ProductSizeJpaEntity entity = getEntity(productSizeInfoUpdateForm.productSizeId());
        entity.changeName(productSizeInfoUpdateForm.name());
    }

    private ProductSizeJpaEntity getEntity(Long productSizeId) {
        return productSizeJpaRepository.findById(productSizeId)
                .orElseThrow(() -> new EntityNotFoundException("해당 아이디를 갖는 상품 사이즈가 없습니다."));
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

    private List<ProductSizeJpaEntity> convertToEntities(
            ProductInfoJpaEntity productInfoJpaEntity,
            List<SizeRegistrationForm> sizeRegistrationForms
    ) {
        return sizeRegistrationForms.stream()
                .map(sizeRegistrationForm ->
                        convertToEntity(productInfoJpaEntity, sizeRegistrationForm)
                ).toList();
    }
}
