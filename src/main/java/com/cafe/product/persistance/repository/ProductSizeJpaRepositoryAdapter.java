package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.dto.ProductSizeDetailViewDto;
import com.cafe.product.persistance.entity.ProductSizeJpaEntity;
import com.cafe.product.service.impl.size.ProductSizeChanger;
import com.cafe.product.service.impl.size.ProductSizeCreator;
import com.cafe.product.service.impl.size.ProductSizeDeleter;
import com.cafe.product.service.impl.size.ProductSizeReader;
import com.cafe.product.service.vo.size.ProductSizeInfoUpdateForm;
import com.cafe.product.service.vo.size.ProductSizePriceUpdateForm;
import com.cafe.product.service.vo.size.SizeRegistrationForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSizeJpaRepositoryAdapter implements ProductSizeCreator, ProductSizeChanger, ProductSizeDeleter, ProductSizeReader {

    private final ProductSizeJpaRepository productSizeJpaRepository;
    private final ProductSizeJdbcRepository productSizeJdbcRepository;

    @Override
    public void createAll(
            Long productInfoId,
            List<SizeRegistrationForm> sizeRegistrationForms
    ) {
        List<ProductSizeJpaEntity> productSizeJpaEntities = sizeRegistrationForms.stream()
                .map(sizeRegistrationForm -> convertToEntity(productInfoId, sizeRegistrationForm))
                .toList();
        productSizeJdbcRepository.saveAll(productSizeJpaEntities);
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

    @Override
    public void deleteByProductSizeId(Long productSizeId) {
        productSizeJpaRepository.deleteById(productSizeId);
    }

    @Override
    public void deleteAllByProductInfoId(Long productInfoId) {
        productSizeJpaRepository.deleteAllByProductInfoId(productInfoId);
    }

    @Override
    public List<ProductSizeDetailViewDto> readAllByProductInfoId(Long productInfoId) {
        List<ProductSizeJpaEntity> productSizeJpaEntities = productSizeJpaRepository.findAllByProductInfoId(productInfoId);
        return productSizeJpaEntities.stream()
                .map(this::convertToSizeDetailViewDto)
                .toList();
    }

    private ProductSizeDetailViewDto convertToSizeDetailViewDto(ProductSizeJpaEntity productSizeJpaEntity) {
        return new ProductSizeDetailViewDto(
                productSizeJpaEntity.getProductSizeId(),
                productSizeJpaEntity.getName(),
                productSizeJpaEntity.getExtraCharge(),
                productSizeJpaEntity.getExtraCost()
        );
    }

    private ProductSizeJpaEntity convertToEntity(
            Long productInfoId,
            SizeRegistrationForm sizeRegistrationForm
    ) {
        return ProductSizeJpaEntity.builder()
                .name(sizeRegistrationForm.name())
                .extraCharge(sizeRegistrationForm.extraCharge())
                .extraCost(sizeRegistrationForm.extraCost())
                .productInfoId(productInfoId)
                .build();
    }
}
