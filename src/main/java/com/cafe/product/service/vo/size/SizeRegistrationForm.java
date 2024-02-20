package com.cafe.product.service.vo.size;

import lombok.Builder;

@Builder
public record SizeRegistrationForm(
        String name,
        int extraCharge,
        int extraCost
) {
}
