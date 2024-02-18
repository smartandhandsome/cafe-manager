package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.SizeRegistrationForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record SizeRegistrationRequest(
        @NotBlank
        String name,
        @PositiveOrZero
        int extraCharge,
        @PositiveOrZero
        int extraCost
) {

    public SizeRegistrationForm toSizeRegistrationForm() {
        return SizeRegistrationForm.builder()
                .name(name)
                .extraCharge(extraCharge)
                .extraCost(extraCost)
                .build();
    }

}
