package com.cafe.product.presentation.request;

import com.cafe.product.service.vo.SizeRegistrationForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "사이즈 등록 요청 객체")
public record SizeRegistrationRequest(
        @Schema(description = "사이즈 이름")
        @NotBlank
        String name,
        @Schema(description = "추가 금액")
        @PositiveOrZero
        int extraCharge,
        @Schema(description = "추가 원가")
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
