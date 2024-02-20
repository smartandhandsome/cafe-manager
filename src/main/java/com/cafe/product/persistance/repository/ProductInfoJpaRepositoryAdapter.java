package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.dto.ProductInfoCategoryDetailViewDto;
import com.cafe.product.persistance.dto.ProductListViewDto;
import com.cafe.product.persistance.entity.ProductInfoJpaEntity;
import com.cafe.product.service.impl.info.ProductInfoChanger;
import com.cafe.product.service.impl.info.ProductInfoCreator;
import com.cafe.product.service.impl.info.ProductInfoDeleter;
import com.cafe.product.service.impl.info.ProductInfoReader;
import com.cafe.product.service.impl.info.ProductInfoSearch;
import com.cafe.product.service.vo.info.PreprocessedProductInfoRegistrationForm;
import com.cafe.product.service.vo.info.ProductDetailInfoUpdateForm;
import com.cafe.product.service.vo.info.ProductPriceInfoUpdateForm;
import com.cafe.product.service.vo.search.NameSearchResult;
import com.cafe.product.service.vo.search.NameSearchView;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ProductInfoJpaRepositoryAdapter implements ProductInfoCreator, ProductInfoReader, ProductInfoChanger, ProductInfoDeleter, ProductInfoSearch {

    private final ProductInfoJpaRepository productInfoJpaRepository;
    private final ProductInfoJdbcRepository productInfoJdbcRepository;

    @Override
    public Long create(PreprocessedProductInfoRegistrationForm form) {
        return productInfoJpaRepository.save(
                convertToEntity(form)
        ).getProductInfoId();
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
    public List<ProductListViewDto> readProductListViewPagination(Long productInfoCursorId, int pageSize) {
        return productInfoJdbcRepository.findProductListViewPagination(productInfoCursorId, pageSize);
    }

    @Override
    public boolean hasProductInfoIdGreaterThan(Long productInfoId) {
        return productInfoJpaRepository.existsByProductInfoIdGreaterThan(productInfoId);
    }

    @Override
    public ProductInfoCategoryDetailViewDto readProductDetail(Long productInfoId) {
        return productInfoJpaRepository.findProductDetail(productInfoId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                MessageFormat.format("해당 아이디와 일치하는 상품 정보가 존재하지 않습니다. [productInfoId: {0}]", productInfoId)
                        )
                );
    }

    @Override
    public void deleteByProductInfoId(Long productInfoId) {
        productInfoJpaRepository.deleteById(productInfoId);
    }

    @Override
    public NameSearchResult searchComplete(String name) {
        List<ProductInfoJpaEntity> entities = productInfoJpaRepository.findByNameContaining(name);
        return new NameSearchResult(entities.stream()
                .map(entity -> new NameSearchView(entity.getProductInfoId(), entity.getName()))
                .toList());
    }

    @Override
    public NameSearchResult searchChosung(String name) {
        List<ProductInfoJpaEntity> entities = productInfoJpaRepository.findByNameChosungContaining(name);
        return new NameSearchResult(entities.stream()
                .map(entity -> new NameSearchView(entity.getProductInfoId(), entity.getName()))
                .toList());
    }

    private ProductInfoJpaEntity getById(Long productInfoId) {
        return productInfoJpaRepository.findById(productInfoId)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("해당 아이디와 일치하는 상품 정보가 존재하지 않습니다. [productInfoId: {0}]", productInfoId)
                ));
    }

    private ProductInfoJpaEntity convertToEntity(PreprocessedProductInfoRegistrationForm domain) {
        return ProductInfoJpaEntity.builder()
                .productCategoryId(domain.productCategoryId())
                .basePrice(domain.basePrice())
                .baseCost(domain.baseCost())
                .name(domain.name())
                .nameChosung(domain.nameChosung())
                .description(domain.description())
                .barcode(domain.barcode())
                .expirationDuration(domain.expirationDuration())
                .build();
    }

}

