package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.entity.ProductSizeJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductSizeJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<ProductSizeJpaEntity> productSizeJpaEntities) {
        jdbcTemplate.batchUpdate("""
                        INSERT INTO product_sizes (name, extra_charge, extra_cost, product_info_id, created_date_time, last_modified_date_time)
                            VALUES (?, ?, ?, ?, ?, ?)
                            """,
                productSizeJpaEntities,
                productSizeJpaEntities.size(),
                (ps, productSizeJpaEntity) -> {
                    LocalDateTime now = LocalDateTime.now();
                    ps.setString(1, productSizeJpaEntity.getName());
                    ps.setInt(2, productSizeJpaEntity.getExtraCharge());
                    ps.setInt(3, productSizeJpaEntity.getExtraCost());
                    ps.setLong(4, productSizeJpaEntity.getProductInfoId());
                    ps.setObject(5, now);
                    ps.setObject(6, now);
                });
    }

}
