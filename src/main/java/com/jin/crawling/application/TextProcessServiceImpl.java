package com.jin.crawling.application;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Getter
public class TextProcessServiceImpl implements TextProcessService {

    private String originText;

    private String text;

    @Override
    public void initTextProcessService(String originText) {
        this.originText = originText;
        this.text = originText;
    }

    public String filterEnglishAndNum() {
        this.text = text.replaceAll("[^0-9a-zA-Z]", "");
        return text;
    }

    public String sortAscending() {

        Character[] chars = text.chars()
                .mapToObj(chr -> (char) chr)
                .toArray(Character[]::new);

        Arrays.sort(chars, (c1, c2) -> {
            char lower1 = Character.toLowerCase(c1);
            char lower2 = Character.toLowerCase(c2);

            if (lower1 == lower2) {
                return Character.compare(c1, c2);
            }
            else {
                boolean c1Digit = Character.isDigit(c1);
                boolean c2Digit = Character.isDigit(c2);

                if (c1Digit == c2Digit) {
                    return Character.compare(lower1, lower2);
                }
                else if (c1Digit) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });

        // TODO: Apache Common Lang 유틸로 변경?
        text = Arrays.stream(chars)
                .map(Object::toString)
                .collect(Collectors.joining());

        return text;
    }

    public String deduplicate() {
        // TODO: 조금더 효울적인 중복제거 없을까??
        // TODO: Set을 사용하면서 Comparater를 같이 넘겨서 내맘대로 정렬을 할수는 없나?
        System.out.println(text);
        Set<Character> charSet = text.chars()
                .mapToObj(chr -> (char) chr)
                .collect(Collectors.toSet());
        System.out.println(charSet);

        text = charSet.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        return text;
    }

    public String crossEnglishAndNum() {

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(text);

        boolean isFind = matcher.find();
        int numberStartIndex = matcher.start();

        if (!isFind || numberStartIndex == 0) {
            return text;
        }


        String englishText = text.substring(0, numberStartIndex);
        String numberText = text.substring(numberStartIndex);
        StringBuilder result = new StringBuilder();

        // TODO: 문자열 substring vs 배열 자르기
        while (englishText.length() > 1 && numberText.length() > 0) {

            String engChar = englishText.substring(0, 1);
            String engChar2 = englishText.substring(1, 2);

            if (engChar.toLowerCase().equals(engChar2)) {
                result.append(engChar).append(engChar2);
                englishText = englishText.substring(2);
            }
            else {
                result.append(engChar);
                englishText = englishText.substring(1);
            }

            String numChar = numberText.substring(0, 1);
            result.append(numChar);
            numberText = numberText.substring(1);
        }

        result.append(englishText);

        if (numberText.length() > 0) {
            result.append(numberText);
        }

        text = result.toString();
        return text;
    }
}
