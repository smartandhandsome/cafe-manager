package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductCategoryJpaEntity;
import com.cafe.product.service.impl.ProductCategoryCreator;
import com.cafe.product.service.impl.ProductCategoryReader;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCategoryJpaRepositoryAdapter implements ProductCategoryCreator, ProductCategoryReader {

    private final ProductCategoryJpaRepository productCategoryJpaRepository;

    @Override
    public void create(ProductCategoryRegistrationForm productInfoRegistrationForm) {
        productCategoryJpaRepository.save(convertToEntity(productInfoRegistrationForm));
    }

    private ProductCategoryJpaEntity convertToEntity(ProductCategoryRegistrationForm domain) {
        return new ProductCategoryJpaEntity(domain.name());
    }

    @Override
    public boolean existsByName(String name) {
        return productCategoryJpaRepository.existsByName(name);
    }
}
