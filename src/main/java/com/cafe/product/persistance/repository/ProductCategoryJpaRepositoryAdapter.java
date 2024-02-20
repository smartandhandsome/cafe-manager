package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductCategoryJpaEntity;
import com.cafe.product.service.impl.ProductCategoryChanger;
import com.cafe.product.service.impl.ProductCategoryCreator;
import com.cafe.product.service.impl.ProductCategoryDeleter;
import com.cafe.product.service.impl.ProductCategoryReader;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductCategoryUpdateForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class ProductCategoryJpaRepositoryAdapter implements ProductCategoryCreator, ProductCategoryReader, ProductCategoryChanger, ProductCategoryDeleter {

    private final ProductCategoryJpaRepository productCategoryJpaRepository;

    @Override
    public void create(ProductCategoryRegistrationForm productCategoryRegistrationForm) {
        productCategoryJpaRepository.save(convertToEntity(productCategoryRegistrationForm));
    }

    @Override
    public boolean existsByName(String name) {
        return productCategoryJpaRepository.existsByName(name);
    }

    @Override
    public void change(ProductCategoryUpdateForm productCategoryUpdateForm) {
        ProductCategoryJpaEntity entity = productCategoryJpaRepository.findById(productCategoryUpdateForm.productCategoryId())
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                MessageFormat.format("해당 카테고리 id가 존재하지 않습니다. [id: {0}]", productCategoryUpdateForm)
                        )
                );
        entity.changeName(productCategoryUpdateForm.name());
    }

    @Override
    public void deleteByProductCategoryId(Long productCategoryId) {
        productCategoryJpaRepository.deleteById(productCategoryId);
    }

    private ProductCategoryJpaEntity convertToEntity(ProductCategoryRegistrationForm domain) {
        return new ProductCategoryJpaEntity(domain.name());
    }

}
