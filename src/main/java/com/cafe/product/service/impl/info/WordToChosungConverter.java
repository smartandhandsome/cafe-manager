package com.cafe.product.service.impl.info;

import org.springframework.stereotype.Component;

@Component
public class WordToChosungConverter {
    public static final char KOREAN_FIRST_CHARACTER = '가';
    public static final char KOREAN_LAST_CHARACTER = '힣';
    private static final int MIDDLE_VOWEL_NUMBER = 21;
    private static final int LAST_CONSONANT_NUMBER = 28;
    private static final char[] FIRST_CONSONANTS = {
            'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ',
            'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };

    public String convert(String word) {
        return word.chars()
                .mapToObj(
                        character -> {
                            if (character >= KOREAN_FIRST_CHARACTER && character <= KOREAN_LAST_CHARACTER) {
                                int index = (character - KOREAN_FIRST_CHARACTER) / (MIDDLE_VOWEL_NUMBER * LAST_CONSONANT_NUMBER);
                                return String.valueOf(FIRST_CONSONANTS[index]);
                            } else {
                                return String.valueOf(character);
                            }
                        })
                .reduce("", (str, character) -> str + character);
    }
}
