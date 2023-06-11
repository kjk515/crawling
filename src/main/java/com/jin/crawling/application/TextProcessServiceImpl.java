package com.jin.crawling.application;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Getter
public class TextProcessServiceImpl implements TextProcessService {

    private String originText;

    private String text;

    private Stream<Character> textStream;

    @Override
    public void initTextProcessService(String originText) {
        this.originText = originText;
        this.text = originText;
        this.textStream = originText.chars().mapToObj(chr -> (char) chr);
    }

    public String filterEnglishAndNum() {
        this.text = text.replaceAll("[^0-9a-zA-Z]", "");
        return text;
    }

    public TextProcessServiceImpl filterEnglishAndNumStream() {
        textStream = textStream.filter(Character::isLetterOrDigit);
        return this;
    }

    public String sortAscending() {

        text = text.chars()
                .mapToObj(chr -> (char) chr)
                .sorted((c1, c2) -> {
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
                })
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

        return text;
    }

    public TextProcessServiceImpl sortAscendingStream() {

        textStream = textStream.sorted((c1, c2) -> {
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

        return this;
    }

    public String deduplicate() {

        // TODO: 조금더 효울적인 중복제거 없을까??
        // TODO: Set을 사용하면서 Comparater를 같이 넘겨서 내맘대로 정렬을 할수는 없나?
        Set<Character> charSet = text.chars()
                .mapToObj(chr -> (char) chr)
                .collect(Collectors.toSet());

        text = charSet.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        return text;
    }

    public TextProcessServiceImpl deduplicateStream() {
        textStream = textStream.distinct();
        return this;
    }

    public TextProcessServiceImpl deduplicateStream2() {
        textStream = textStream.collect(Collectors.toSet()).stream();
        return this;
    }

    public TextProcessServiceImpl deduplicateStream3() {
        textStream = textStream.collect(Collectors.toCollection(LinkedHashSet::new)).stream();
        return this;
    }


    public String crossEnglishAndNum() {

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(text);

        boolean isFind = matcher.find();

        if (!isFind || matcher.start() == 0) {
            return text;
        }

        int numberStartIndex = matcher.start();

        String englishText = text.substring(0, numberStartIndex);
        String numberText = text.substring(numberStartIndex);
        StringBuilder result = new StringBuilder();

        int engTextLength = englishText.length();
        int numTextLength = numberText.length();

        int engIndex = 0;
        int numIndex = 0;

        // TODO: 문자열 substring vs 배열 자르기
        while (engIndex < engTextLength && numIndex < numTextLength) {

            char engChar = englishText.charAt(engIndex);
            char engChar2 = englishText.charAt(engIndex + 1);

            if (Character.toLowerCase(engChar) == Character.toLowerCase(engChar2)) {
                result.append(engChar).append(engChar2);
                engIndex += 2;
            }
            else {
                result.append(engChar);
                engIndex += 1;
            }

            char numChar = numberText.charAt(numIndex);
            result.append(numChar);
            numIndex += 1;
        }

        result.append(englishText.substring(engIndex));
        result.append(numberText.substring(numIndex));

        text = result.toString();
        return text;
    }

    public String buildString() {

        // TODO: vs .map(String::valueOf).collect(Collectors.joining());

        return textStream
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
