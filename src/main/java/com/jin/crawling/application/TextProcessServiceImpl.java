package com.jin.crawling.application;

import lombok.Getter;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
public class TextProcessServiceImpl implements TextProcessService {

    private final String originText;

    private String text;


    public TextProcessServiceImpl(String originText) {
        this.originText = originText;
        this.text = originText;
    }

    public String filterEnglishAndNum() {
        this.text = text.replaceAll("[^0-9a-zA-Z]", "");
        return text;
    }

    public String sortAscending() {
        // TODO: 정렬을 오름차순이 아닌, 과제 기준으로 해야 함.
//        char[] chars = text.toCharArray();

        Character[] chars = text.chars()
                .mapToObj(chr -> (char) chr)
                .toArray(Character[]::new);

        Arrays.sort(chars, (c1, c2) -> {
            char lower1 = Character.toLowerCase(c1);
            char lower2 = Character.toLowerCase(c2);

            if (c1.equals(c2)) {
                return 0;
            }
            else if (lower1 == lower2) {
                return c1 - c2;
            }
            else {
                boolean c1Digit = Character.isDigit(c1);
                boolean c2Digit = Character.isDigit(c2);

                if (c1Digit == c2Digit) {
                    return lower1 - lower2;
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
                .collect( Collectors.joining() );

        return text;
    }

    public String deduplicate() {
        // TODO: 조금더 효울적인 중복제거 없을까??
        Set<Character> charSet = text.chars()
                .mapToObj(chr -> (char) chr)
                .collect(Collectors.toSet());

        text = charSet.stream().map(String::valueOf).collect(Collectors.joining());

        return text;
    }

    public String crossEnglishAndNum() {

//        String[] englishAndNum = text.split("0");
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(text);

        boolean isFind = matcher.find();

        if (!isFind || matcher.start() == 0) {
            return text;
        }

        String englishText = text.substring(0, matcher.start());
        String numberText = text.substring(matcher.start());

        StringBuilder result = new StringBuilder();
        result.append(englishText.charAt(0));
        String lastChar = "";
        int englishIndex = 1;
        int numberIndex = 0;


        for (int i = 0; i < englishText.length() + numberText.length() - 1; i++) {

            if (englishIndex >= englishText.length()) {
                result.append(numberText.charAt(numberIndex));
                numberIndex++;
            }
            else if (numberIndex >= numberText.length()) {
                result.append(englishText.charAt(englishIndex));
                englishIndex++;
            }
            else {
//                result.g

                String englishChar = englishText.substring(englishIndex, 1);
                String numberChar = englishText.substring(englishIndex, 1);
            }
        }


        System.out.println(englishText);
        System.out.println(numberText);
        System.out.println(result);
        return text;
    }
}
