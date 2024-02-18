package com.cafe.product.service.vo;

import lombok.Builder;

@Builder
public record SizeRegistrationForm(
        String name,
        int extraCharge,
        int extraCost
) {
}
