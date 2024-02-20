package com.cafe.product.service.vo.search;

import com.cafe.common.constants.RegexPattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.regex.Pattern;

@Getter
@RequiredArgsConstructor
public enum NameSearchStrategy {
    CHOSUNG(Pattern.compile(RegexPattern.CHOSUNG)),
    DEFAULT(Pattern.compile("")),
    ;

    private final Pattern pattern;

    public static NameSearchStrategy determine(String name) {
        return Arrays.stream(values())
                .filter(nameSearchStrategy -> nameSearchStrategy.isMatched(name))
                .findAny()
                .orElse(DEFAULT);
    }

    public static void main(String[] args) {
        String fafasdfasd = "fafasdfasd";
        System.out.println("COMPLETE_WORD.pattern.matcher(fafasdfasd).matches() = " + DEFAULT.pattern.matcher(fafasdfasd).matches());
    }

    private boolean isMatched(String name) {
        return pattern.matcher(name)
                .matches();
    }

}
