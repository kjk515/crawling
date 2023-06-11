package com.jin.crawling.application;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
@Getter
public class TextProcessServiceImpl implements TextProcessService {

    private final int MIN_PARALLEL_SIZE = 100_000;

    private Stream<Character> textStream;

    @Override
    public TextProcessService initTextProcessService(String text) {

        String replacedText = text.replaceAll("[^0-9a-zA-Z]", "");
        this.textStream = this.toStream(replacedText);

        if (text.length() > MIN_PARALLEL_SIZE) {
            this.textStream = this.textStream.parallel();
        }
        return this;
    }

    public TextProcessServiceImpl sort() {

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

    public TextProcessServiceImpl distinct() {
        textStream = textStream.distinct();
        return this;
    }

    public TextProcessService crossEnglishAndNum() {

        String text = this.buildString();

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(text);

        boolean isFind = matcher.find();

        if (!isFind || matcher.start() == 0) {
            this.textStream = toStream(text);
            return this;
        }


        int numberStartIndex = matcher.start();

        String englishText = text.substring(0, numberStartIndex);
        String numberText = text.substring(numberStartIndex);
        StringBuilder result = new StringBuilder();

        int engTextLength = englishText.length();
        int numTextLength = numberText.length();

        int engIndex = 0;
        int numIndex = 0;

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

        this.textStream = toStream(result.toString());
        return this;
    }

    public String buildString() {
        return textStream
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private Stream<Character> toStream(String text) {
        return text.chars().mapToObj(chr -> (char) chr);
    }
}
