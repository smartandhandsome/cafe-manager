package com.cafe.product.presentation.request;

public enum SizeRegistrationRequestFixture {
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

    SizeRegistrationRequestFixture(String name, int extraCharge, int extraCost) {
        this.name = name;
        this.extraCharge = extraCharge;
        this.extraCost = extraCost;
    }

    public SizeRegistrationRequest newInstance() {
        return new SizeRegistrationRequest(
                name,
                extraCharge,
                extraCost
        );
    }

    private static final class Constants {
        private static final String SMALL = "Small";
        private static final String LARGE = "Large";
        private static final int ZERO = 0;
    }

}
