package com.cafe.common.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class RegexPattern {

    public static final String PHONE_REGEX = "^01([016789])-\\d{3,4}-\\d{4}$";
    public static final String CHOSUNG = "^[ㄱ-ㅎ]+$";
    public static final String COMPLETE_WORD = "^[가-힣]+$";

}
