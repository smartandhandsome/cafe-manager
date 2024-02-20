package com.cafe.product.service.vo;

import com.cafe.product.service.vo.size.SizeRegistrationForm;

public enum SizeRegistrationFormFixture {
    SMALL(
            Constants.SMALL,
            Constants.ZERO,
            Constants.ZERO
    ),
    LARGE(
            Constants.LARGE,
            Constants.ZERO,
            Constants.ZERO
    ),
    ;


    private final String name;
    private final int extraCharge;
    private final int extraCost;

    SizeRegistrationFormFixture(String name, int extraCharge, int extraCost) {
        this.name = name;
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
    }

    public SizeRegistrationForm newInstance() {
        return new SizeRegistrationForm(
                name,
                extraCharge,
                extraCost
        );
    }

    private static final class Constants {
        private final static String SMALL = "Small";
        private final static String LARGE = "Large";
        private final static int ZERO = 0;
    }
}
