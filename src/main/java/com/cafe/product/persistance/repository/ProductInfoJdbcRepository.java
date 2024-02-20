package com.cafe.product.persistance.repository;

import com.cafe.product.persistance.dto.ProductListViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductInfoJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<ProductListViewDto> findProductListViewPagination(Long cursorId, int pageSize) {
        String sql = """
                SELECT i.product_info_id, c.product_category_id, c.name category_name, i.name info_name, i.base_price 
                FROM product_infos i
                LEFT JOIN product_categories c ON i.product_category_id = c.product_category_id
                WHERE ? < i.product_info_id
                LIMIT ?
                """;
        return jdbcTemplate.query(
                sql,
                ps -> {
                    ps.setLong(1, cursorId);
                    ps.setInt(2, pageSize);
                },
                (rs, rowNum) -> {
                    Long productInfoId = rs.getLong("product_info_id");
                    Long productCategoryId = rs.getLong("product_category_id");
                    String categoryName = rs.getString("category_name");
                    String infoName = rs.getString("info_name");
                    BigDecimal basePrice = rs.getBigDecimal("base_price");
                    return new ProductListViewDto(productInfoId, productCategoryId, categoryName, infoName, basePrice.intValue());
                });
    }
}
